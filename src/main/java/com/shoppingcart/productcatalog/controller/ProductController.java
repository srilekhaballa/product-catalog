package com.shoppingcart.productcatalog.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lowagie.text.DocumentException;
import com.shoppingcart.productcatalog.common.ApiResponse;
import com.shoppingcart.productcatalog.dto.product.ProductDto;
import com.shoppingcart.productcatalog.exceptions.ProductNotExistException;
import com.shoppingcart.productcatalog.model.Category;
import com.shoppingcart.productcatalog.model.Product;
import com.shoppingcart.productcatalog.repository.ProductRepository;
import com.shoppingcart.productcatalog.service.CategoryService;
import com.shoppingcart.productcatalog.service.ExcelService;
import com.shoppingcart.productcatalog.service.ProductService;
import com.shoppingcart.productcatalog.utils.ProductPDFExporter;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired 
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ExcelService excelService;
    
    @GetMapping("/get")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) throws ProductNotExistException{
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<String>( "category is invalid", HttpStatus.CONFLICT);
        }
        
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<String>("Product has been added", HttpStatus.CREATED);
    }

    @PutMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Validated ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
    
   /*@GetMapping("/export/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= products_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Product> listProducts = productService.listAll();
        ProductExcelExporter excelExporter = new ProductExcelExporter(listProducts);
        excelExporter.export(response);    
    }  */
    
    
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Product> listProducts = productService.listAll();
         
        ProductPDFExporter exporter = new ProductPDFExporter(listProducts);
        exporter.export(response);
    }
        @GetMapping("/export/excel")
        public ResponseEntity<Resource> getFile() {
            String filename = "products.xlsx";
            InputStreamResource file = new InputStreamResource(excelService.load());

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
          }
}