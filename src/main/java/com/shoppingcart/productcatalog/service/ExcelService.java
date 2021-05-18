package com.shoppingcart.productcatalog.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shoppingcart.productcatalog.model.Product;
import com.shoppingcart.productcatalog.repository.ProductRepository;
import com.shoppingcart.productcatalog.utils.ExcelHelper;

@Service
public class ExcelService {
  @Autowired
  ProductRepository productRepository;

  public ByteArrayInputStream load() {
    List<Product> products = productRepository.findAll();

    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(products);
    return in;
  }

}
