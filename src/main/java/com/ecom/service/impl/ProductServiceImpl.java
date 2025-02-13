package com.ecom.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entity.Product;
import com.ecom.repository.ProductRepo;
import com.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {

		return productRepo.save(product);

	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Boolean deleteProduct(int id) {

		Product product = productRepo.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(product)) {
			productRepo.delete(product);
			return true;
		}
		return false;
	}

	@Override
	public Product getProductById(int id) {

		Product product = productRepo.findById(id).orElse(null);
		return product;
	}

	@Override
	public Product updateProduct(Product product, MultipartFile image) {

		Product oldProduct = getProductById(product.getId());

		String imageName = image.isEmpty() ? oldProduct.getImage() : image.getOriginalFilename();

		oldProduct.setImage(imageName);
		oldProduct.setTitle(product.getTitle());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setStock(product.getStock());
		oldProduct.setDiscount(product.getDiscount());
		oldProduct.setIsActive(product.getIsActive());

		double discount = product.getPrice() * product.getDiscount() / 100.0;
		double discountPrice = product.getPrice() - discount;
		oldProduct.setDiscountPrice(discountPrice);

		Product updateProduct = productRepo.save(oldProduct);
		if (!ObjectUtils.isEmpty(updateProduct)) {
			if (!image.isEmpty()) {

				try {

					File myFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(myFile.getAbsolutePath() + File.separator + "product_img" + File.separator
							+ image.getOriginalFilename());
					Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			return updateProduct;
		}
		return null;
	}

	@Override
	public List<Product> getAllActiveProducts(String category) {
		List<Product> activeProducts = null;
		if (ObjectUtils.isEmpty(category)) {
			activeProducts = productRepo.findByIsActiveTrue();
		} else {
			activeProducts = productRepo.findByCategory(category);

		}

		return activeProducts;
	}

}
