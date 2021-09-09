import {
  VuexModule,
  Module,
  Mutation,
  Action,
  getModule
} from 'vuex-module-decorators'
import { infoRecivedOrder } from '@/api/recived-order'
import store from '@/store'
export interface IRecivedOrderListState {
  list: JSON[]
  totalItem: number
}

@Module({ dynamic: true, store, name: 'client'})
class RecivedOrderList extends VuexModule implements IRecivedOrderListState {
  public list: JSON[] = []
  public totalItem = 0

  @Mutation
  private SET_LIST(list: JSON[]) {
    this.list = list
  }

  @Mutation
  private SET_TOTAL_ITEM(totalItem: number) {
    this.totalItem = totalItem
  }

  @Action({ rawError: true })
  public async RecivedOrderList(recivedOrderInfo: any) {
    const { data } = await infoRecivedOrder(recivedOrderInfo)
    this.SET_LIST(data.recivedOrder)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }
}

export const RecivedOrderListModule = getModule(RecivedOrderList)