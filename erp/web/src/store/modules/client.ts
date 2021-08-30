import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createClient, getClient, updateClient, infoClient } from '@/api/client'
import store from '@/store'
export interface IClientState {
  id: string,
  name: string
}

@Module({ dynamic: true, store, name: 'client' })
class Client extends VuexModule implements IClientState {
  public id = ''
  public name = ''
  @Mutation
  private SET_ID(id: string){
    this.id = id
  }

  @Mutation
  private SET_NAME(name: string){
    this.name = name
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
}

export const ClientModule = getModule(Client)