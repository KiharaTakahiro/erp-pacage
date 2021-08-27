import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createClient, getClient, updateClient, infoClient } from '@/api/client'
import store from '@/store'
export interface IClientState {
  id: string,
  name: string,
  list: JSON[]
}

@Module({ dynamic: true, store, name: 'client' })
class Client extends VuexModule implements IClientState {
  public id = ''
  public name = ''
  public list: JSON[] = []

  @Mutation
  private SET_ID(id: string){
    this.id = id
  }

  @Mutation
  private SET_NAME(name: string){
    this.name = name
  }

  @Mutation
  private SET_LIST(list: JSON[]){
    this.list = list
  }

  @Action
  public async CreateClient(clientInfo: { name: string }) {
    let { name } = clientInfo
    name = name.trim()
    await createClient({ clientsName: name })
  }

  @Action
  public setClientsName(name: string) {
    // 公式的にはcommitをしなきゃいけないらしい。（コンポーネントで呼び出すか、ストアで呼び出すかは非同期処理でなければどっちでもいいらしい。）
    // this.SET_NAME(name)
    store.commit('SET_NAME', name)
  }

  
  @Action({ rawError: true })
  public async EditClient(clientInfo: { id: string }) {
    let { id } = clientInfo
    const { data } = await getClient({ clientsSeq: id })
    this.SET_ID(data.clientsSeq)
    this.SET_NAME(data.clientsName)
  }

  @Action({ rawError: true })
  public async UpdateClient(clientInfo: { id: string, name: string }) {
    let {id, name } = clientInfo
    name = name.trim()
    await updateClient({ clientsName: name, clientsSeq: id })
  }

  @Action({ rawError: true })
  public async ClientList(clientInfo: any){
    const { data } = await infoClient(clientInfo)
    return data.clients

    }
}

export const ClientModule = getModule(Client)