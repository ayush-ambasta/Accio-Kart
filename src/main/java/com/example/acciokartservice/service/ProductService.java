package com.example.acciokartservice.service;

import com.example.acciokartservice.dto.request.ProductRequest;
import com.example.acciokartservice.exception.SellerNotFoundException;
import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.model.Seller;
import com.example.acciokartservice.repository.ProductRepository;
import com.example.acciokartservice.repository.SellerRepository;
import com.example.acciokartservice.service.transfomer.ProductTransformer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

//    public ProductService(SellerRepository sellerRepository) {
//        this.sellerRepository = sellerRepository;
//    }

    public Product addProduct(String sellerUniqueNumber,
                              ProductRequest productRequest) {

        Optional<Seller> optionalSeller = sellerRepository.findByUniqueSellerNumber(sellerUniqueNumber);
        if(optionalSeller.isEmpty()){
            throw new SellerNotFoundException("Invalid seller number!!!");
        }

        Seller seller = optionalSeller.get();

        Product product = ProductTransformer.productRequestToProduct(productRequest);

        // setting both the
        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller);
        return savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

    }


    public Page<Product> getAllProducts(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("desc")? Sort.Order.desc(sortBy):Sort.Order.asc(sortBy));
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        return productRepository.findAll(pageable);
    }
}