import {
  VuexModule,
  Module,
  Mutation,
  Action,
  getModule
} from 'vuex-module-decorators'
import { updateClient, infoClient } from '@/api/client'
import store from '@/store'
export interface IClientListState {
  list: JSON[]
  totalItem: number
}

@Module({ dynamic: true, store, name: 'client' })
class ClientList extends VuexModule implements IClientListState {
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
  public async UpdateClient(clientInfo: { id: string; name: string }) {
    let { id, name } = clientInfo
    name = name.trim()
    await updateClient({ clientsName: name, clientsSeq: id })
  }

  @Action({ rawError: true })
  public async ClientList(clientInfo: any) {
    const { data } = await infoClient(clientInfo)
    this.SET_LIST(data.clients)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }
}

export const ClientListModule = getModule(ClientList)
