import { VuexModule, Module, Action, Mutation, getModule } from 'vuex-module-decorators'
import {  createProduct, getProduct, updateProduct, infoProduct } from '@/api/product'
import store from '@/store'

export interface IProductState {
  productName: string
  taxType: number
  purchaseUnitPrice: bigint
  unitPrice: bigint
  list: JSON[]
  totalItem: number
}
@Module({ dynamic: true, store, name: 'product' })
class Product extends VuexModule implements IProductState{
  public productName = ''
  public taxType = 0
  public purchaseUnitPrice = 0n
  public unitPrice = 0n
  public list: JSON[] = []
  public totalItem = 0
  
  @Mutation
  private SET_NAME(productName: string){
    this.productName = productName
  }
  @Mutation
  private SET_TAXTYPE(taxType: number){
    this.taxType = taxType
  }
  @Mutation
  private SET_PURCHACEUNITPRICE(purchaseUnitPrice: bigint){
    this.purchaseUnitPrice = purchaseUnitPrice
  }
  @Mutation
  private SET_UNITPRICE(unitPrice: bigint){
    this.unitPrice = unitPrice
  }

  
  @Mutation
  private SET_LIST(list: JSON[]){
    this.list = list
  }

  @Mutation
  private SET_TOTAL_ITEM(totalItem: number) {
    this.totalItem = totalItem
  }

  @Action
  public async CreateProduct(productInfo: { productName: string,  taxType: number, purchaseUnitPrice: bigint, unitPrice: bigint }) {
    let { productName, taxType, purchaseUnitPrice, unitPrice } = productInfo
    productName = productName.trim()
    await createProduct({ productName: productName, taxType: taxType, purchaseUnitPrice: purchaseUnitPrice, unitPrice: unitPrice})
  }

  @Action({ rawError: true })
  public async ProductList(productInfo: any){
    const { data } = await infoProduct(productInfo)
    this.SET_LIST(data.product)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }

}
export const ProductModule = getModule(Product)
