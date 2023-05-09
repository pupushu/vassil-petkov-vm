package com.vassil.petkov.vendingmachine.services.implementations;

import com.vassil.petkov.vendingmachine.constants.Constants;
import com.vassil.petkov.vendingmachine.exceptions.ProductNotFoundException;
import com.vassil.petkov.vendingmachine.models.products.UpdateProductModel;
import com.vassil.petkov.vendingmachine.models.products.VendingMachineProductModel;
import com.vassil.petkov.vendingmachine.services.ProductsService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private List<VendingMachineProductModel> allProducts = new ArrayList<>();

    @Override
    public boolean addProduct(VendingMachineProductModel product) {
        boolean spaceAvailable = isThereSpaceAvailable();
        if (spaceAvailable) {
            allProducts.add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(UpdateProductModel updateProduct) throws ProductNotFoundException {
        VendingMachineProductModel oldProduct = extractSingleProductFromData(updateProduct.getOldProductId());
            if (oldProduct != null) {
                oldProduct.setName(updateProduct.getNewProduct().getName());
                oldProduct.setPrice(updateProduct.getNewProduct().getPrice());
                oldProduct.setQuantity(updateProduct.getNewProduct().getQuantity());
                saveProductToData(oldProduct);
                return true;
            }

        return false;
    }


    @Override
    public boolean removeProduct(String productId){
        try {
            VendingMachineProductModel productExist = checkIfProductExist(productId);
            allProducts.remove(productExist);
            return true;
        }catch (ProductNotFoundException e) {
            return false;
        }
    }


    @Override
    public VendingMachineProductModel extractSingleProductFromData(String productId) throws ProductNotFoundException {
        Optional<VendingMachineProductModel> result = allProducts.stream().filter(p -> p.getId().equals(productId)).findAny();
        if (result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    @Override
    public void lowerProductQuantity(VendingMachineProductModel result) throws ProductNotFoundException {
        result.setQuantity(result.getQuantity() - 1);
        updateProduct(new UpdateProductModel(result.getId(), result));
    }

    private VendingMachineProductModel checkIfProductExist(String productId) throws ProductNotFoundException {
        Optional<VendingMachineProductModel> product = allProducts.stream()
                .filter(x -> x.getId().equals(productId))
                .findFirst();
        if (product.isPresent()){
            return product.get();
        }
        throw new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
    }


    private void saveProductToData(VendingMachineProductModel newProduct) throws ProductNotFoundException {
        VendingMachineProductModel productToUpdate = extractSingleProductFromData(newProduct.getId());
        int indexToUpdate = allProducts.indexOf(productToUpdate);
        allProducts.remove(productToUpdate);
        allProducts.add(indexToUpdate, newProduct);
    }

    private boolean isThereSpaceAvailable() {
        return this.allProducts.size() < Constants.MAXIMUM_PRODUCTS_AVAILABLE;
    }


    public List<VendingMachineProductModel> getAllProducts() {
        return allProducts;
    }
}
