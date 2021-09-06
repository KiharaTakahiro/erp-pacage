import {
  VuexModule,
  Module,
  Mutation,
  Action,
  getModule
} from 'vuex-module-decorators'
import store from '@/store'
export interface IDepartmentState {
  id: string
  companyId: string
  name: string
}

@Module({ dynamic: true, store, name: 'client' })
class Department extends VuexModule implements IDepartmentState {
  public id = ''
  public companyId = ''
  public name = ''
  @Mutation
  private SET_ID(id: string) {
    this.id = id
  }

  @Mutation
  private SET_NAME(name: string) {
    this.name = name
  }

  @Mutation
  private SET_COMPANY_ID(companyId: string) {
    this.companyId = companyId
  }
}
export const DepartmentModule = getModule(Department)
