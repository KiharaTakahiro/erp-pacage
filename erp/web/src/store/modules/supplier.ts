import {
  VuexModule,
  Module,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'
import {
  createSupplier,
  getSupplier,
  updateSupplier,
  infoSupplier
} from '@/api/supplier'
import store from '@/store'


export interface ISupplierState {
  id: string
  supplierName: string
  list: JSON[]
  totalItem: number
}

@Module({ dynamic: true, store, name: 'supplier' })
class Supplier extends VuexModule implements ISupplierState {
  public id = ''
  public supplierName = ''
  public list: JSON[] = []
  public totalItem = 0

  @Mutation
  private SET_ID(id: string) {
    this.id = id
  }

  @Mutation
  private SET_NAME(supplierName: string) {
    this.supplierName = supplierName
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
  public async CreateSupplier(supplierInfo: {
    supplierName: string
  }) {
    let { supplierName } = supplierInfo
    supplierName = supplierName.trim()
    await createSupplier({
      supplierName: supplierName,
    })
  }

  @Action
  public setSupplierName(supplierName: string) {
    this.SET_NAME(supplierName)
  }

  @Action({ rawError: true })
  public async EditSupplier(supplierInfo: { id: string }) {
    let { id } = supplierInfo
    const { data } = await getSupplier({ supplierSeq: id })
    this.SET_ID(data.supplierSeq)
    this.SET_NAME(data.supplierName)
  }

  @Action({ rawError: true })
  public async UpdateSupplier(supplierInfo: {
    id: string
    supplierName: string
  }) {
    let { id, supplierName } = supplierInfo
    supplierName = supplierName.trim()
    await updateSupplier({
      supplierSeq: id,
      supplierName: supplierName
    })
  }

  @Action({ rawError: true })
  public async SupplierList(supplierInfo: any) {
    const { data } = await infoSupplier(supplierInfo)
    console.log(data)
    this.SET_LIST(data.supplier)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }
}
export const SupplierModule = getModule(Supplier)