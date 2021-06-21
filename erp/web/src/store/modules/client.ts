import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createClient, editClientPage } from '@/api/client'
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
  private SET_NAME(name: string){
    this.name = name
  }

  @Action
  public async CreateClient(clientInfo: { name: string}) {
    let { name } = clientInfo
    name = name.trim()
    await createClient({clientsName: name})
  }

  
  @Action
  public async EditClientPage(clientInfo: { id: string}) {
    let { id } = clientInfo
    id = id.trim()
    console.log(id)
    await editClientPage({clientsId: id})
  }
}

export const ClientModule = getModule(Client)