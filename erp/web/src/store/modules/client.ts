import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createClient, getClient, updateClient } from '@/api/client'
import store from '@/store'
import elementVariables from '@/styles/element-variables.scss'
import defaultSettings from '@/settings'
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
    await createClient({clientsName: name})
  }

  
  @Action
  public async EditClient(clientInfo: { id: string }) {
    let { id } = clientInfo
    id = id.trim()
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
}

export const ClientModule = getModule(Client)