import {
  VuexModule,
  Module,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'
import {
  createSupplierProduct,
  getSupplierProduct,
  updateSupplierProduct,
  infoSupplierProduct
} from '@/api/supplier-product'
import store from '@/store'

export interface ISupplierProductState {
  id: string
  supplierSeq: string
  supplierProductName: string
  taxType: number
  purchaseUnitPrice: number
  list: JSON[]
  totalItem: number
}
@Module({ dynamic: true, store, name: 'supplierProduct' })
class SupplierProduct extends VuexModule implements ISupplierProductState {
  public id = ''
  public supplierSeq = ''
  public supplierProductName = ''
  public taxType = 0
  public purchaseUnitPrice = 0
  public list: JSON[] = []
  public totalItem = 0

  @Mutation
  private SET_ID(id: string) {
    this.id = id
  }

  @Mutation
  private SET_SUPPLIERSEQ(supplierSeq: string) {
    this.supplierSeq = supplierSeq
  }

  @Mutation
  private SET_NAME(supplierProductName: string) {
    this.supplierProductName = supplierProductName
  }

  @Mutation
  private SET_TAXTYPE(taxType: number) {
    this.taxType = taxType
  }

  @Mutation
  private SET_PURCHACEUNITPRICE(purchaseUnitPrice: number) {
    this.purchaseUnitPrice = purchaseUnitPrice
  }

  @Mutation
  private SET_LIST(list: JSON[]) {
    this.list = list
  }

  @Mutation
  private SET_TOTAL_ITEM(totalItem: number) {
    this.totalItem = totalItem
  }

  @Action
  public async CreateSupplierProduct(supplierProductInfo: {
    supplierSeq: string
    supplierProductName: string
    taxType: number
    purchaseUnitPrice: number
  }) {
    let { supplierSeq, supplierProductName, taxType, purchaseUnitPrice} = supplierProductInfo
    supplierSeq = supplierSeq.trim()
    supplierProductName = supplierProductName.trim()
    await createSupplierProduct({
      supplierSeq: supplierSeq,
      supplierProductName: supplierProductName,
      taxType: taxType,
      purchaseUnitPrice: purchaseUnitPrice,
    })
  }

  @Action
  public setSupplierProductName(supplierProductName: string) {
    this.SET_NAME(supplierProductName)
  }

  @Action({ rawError: true })
  public async EditSupplierProduct(supplierProductInfo: { id: string }) {
    let { id } = supplierProductInfo
    const { data } = await getSupplierProduct({ supplierProductSeq: id })
    this.SET_ID(data.supplierProductSeq)
    this.SET_NAME(data.supplierProductName)
    this.SET_PURCHACEUNITPRICE(data.purchaseUnitPrice)
    this.SET_TAXTYPE(data.taxType)
  }

  @Action({ rawError: true })
  public async UpdateSupplierProduct(supplierProductInfo: {
    id: string
    supplierProductName: string
    taxType: number
    purchaseUnitPrice: number
  }) {
    let { id, supplierProductName, taxType, purchaseUnitPrice} = supplierProductInfo
    supplierProductName = supplierProductName.trim()
    await updateSupplierProduct({
      supplierProductSeq: id,
      supplierProductName: supplierProductName,
      purchaseUnitPrice: purchaseUnitPrice,
      taxType: taxType
    })
  }

  @Action({ rawError: true })
  public async SupplierProductList(supplierProductInfo: any) {
    const { data } = await infoSupplierProduct(supplierProductInfo)
    console.log(data)
    this.SET_LIST(data.supplierProduct)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }
}
export const SupplierProductModule = getModule(SupplierProduct)
