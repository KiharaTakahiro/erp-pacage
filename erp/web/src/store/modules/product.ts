import { VuexModule, Module, Action, Mutation, getModule } from 'vuex-module-decorators'
import {  createProduct, getProduct, updateProduct, infoProduct } from '@/api/product'
import store from '@/store'

export interface IProductState {
  productName: string
  taxType: string
  purchaseUnitPrice: string
  unitPrice: string
}
@Module({ dynamic: true, store, name: 'product' })
class Product extends VuexModule implements IProductState{
  public productName = ''
  public taxType = ''
  public purchaseUnitPrice = ''
  public unitPrice = ''
  
  @Mutation
  private SET_NAME(productName: string){
    this.productName = productName
  }
  @Mutation
  private SET_TAXTYPE(taxType: string){
    this.taxType = taxType
  }
  @Mutation
  private SET_PURCHACEUNITPRICE(purchaseUnitPrice: string){
    this.purchaseUnitPrice = purchaseUnitPrice
  }
  @Mutation
  private SET_UNITPRICE(unitPrice: string){
    this.unitPrice = unitPrice
  }

  @Action
  public async CreateProduct(productInfo: { productName: string,  taxType: string, purchaseUnitPrice: string, unitPrice: string }) {
    let { productName, taxType, purchaseUnitPrice, unitPrice } = productInfo
    productName = productName.trim()
    taxType = taxType.trim()
    purchaseUnitPrice = purchaseUnitPrice.trim()
    unitPrice = unitPrice.trim()
    await createProduct({ productName: productName, taxType: taxType, purchaseUnitPrice: purchaseUnitPrice, unitPrice: unitPrice})
  }

}
export const ProductModule = getModule(Product)
