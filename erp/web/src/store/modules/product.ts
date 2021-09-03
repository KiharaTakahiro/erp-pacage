import { VuexModule, Module, Action, Mutation, getModule } from 'vuex-module-decorators'
import {  createProduct, getProduct, updateProduct, infoProduct } from '@/api/product'
import store from '@/store'

export interface IProductState {
  id: string,
  productName: string
  taxType: number
  purchaseUnitPrice: bigint
  unitPrice: bigint
  list: JSON[]
  totalItem: number
}
@Module({ dynamic: true, store, name: 'product' })
class Product extends VuexModule implements IProductState{
  public id = ''
  public productName = ''
  public taxType = 0
  public purchaseUnitPrice = 0n
  public unitPrice = 0n
  public list: JSON[] = []
  public totalItem = 0
  
  @Mutation
  private SET_ID(id: string){
    this.id = id
  }

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

  @Action
  public setProductName(productName: string) {
    // 公式的にはcommitをしなきゃいけないらしい。（コンポーネントで呼び出すか、ストアで呼び出すかは非同期処理でなければどっちでもいいらしい。）
    // this.SET_NAME(productName)
    store.commit('SET_NAME', productName)
  }

  @Action({ rawError: true })
  public async EditProduct(productInfo: { id: string }) {
    let { id } = productInfo
    const { data } = await getProduct({ productSeq: id })
    this.SET_ID(data.productSeq)
    this.SET_NAME(data.productName)
    this.SET_PURCHACEUNITPRICE(data.purchaseUnitPrice)
    this.SET_UNITPRICE(data.unitPrice)
    this.SET_TAXTYPE(data.taxType)
  }

  @Action({ rawError: true })
  public async UpdateProduct(productInfo: { id: string, productName: string,  taxType: number, purchaseUnitPrice: bigint, unitPrice: bigint }) {
    let {id, productName } = productInfo
    productName = productName.trim()
    await updateProduct({ productName: productName, productsSeq: id })
  }

  @Action({ rawError: true })
  public async ProductList(productInfo: any){
    const { data } = await infoProduct(productInfo)
    this.SET_LIST(data.product)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }

}
export const ProductModule = getModule(Product)
