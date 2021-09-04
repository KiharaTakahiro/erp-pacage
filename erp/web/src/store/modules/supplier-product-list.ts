import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { infoSupplierProduct } from '@/api/supplier-product'
import store from '@/store'
export interface ISupplierProductListState {
  list: JSON[]
  totalItem: number
}

@Module({ dynamic: true, store, name: 'supplierProductName' })
class SupplierProductList extends VuexModule implements ISupplierProductListState {
  public list: JSON[] = []
  public totalItem=0

  @Mutation
  private SET_LIST(list: JSON[]){
    this.list = list
  }

  @Mutation
  private SET_TOTAL_ITEM(totalItem: number) {
    this.totalItem = totalItem
  }

  @Action({ rawError: true })
  public async SupplierProductList(supplierProductInfo: any){
    const { data } = await infoSupplierProduct(supplierProductInfo)
    this.SET_LIST(data.supplierProducts)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }
}

export const SupplierProductModule = getModule(SupplierProductList)