import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createClient } from '@/api/client'
import store from '@/store'
import elementVariables from '@/styles/element-variables.scss'
import defaultSettings from '@/settings'
export interface IClientState {
  name: string
}

@Module({ dynamic: true, store, name: 'client' })
class Client extends VuexModule implements IClientState {
  public name = ''

  @Action
  public async Create(clientInfo: { name: string}) {
    let { name } = clientInfo
    name = name.trim()
    // FIXME: 試してみる
    await createClient({clientsName: name})
  }
}

export const ClientModule = getModule(Client)