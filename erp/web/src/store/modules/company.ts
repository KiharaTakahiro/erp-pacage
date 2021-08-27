import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import store from '@/store'
export interface ICompanyState {
  id: string,
  name: string,
  address: string,
  phone: string,
  zip: string
}

@Module({ dynamic: true, store, name: 'client' })
class Company extends VuexModule implements ICompanyState {
  public id = ""
  public name = ""
  public address = ""
  public phone = ""
  public zip = ""

  @Mutation
  private SET_ID(id: string){
    this.id = id
  }

  @Mutation
  private SET_NAME(name: string){
    this.name = name
  }

  @Mutation
  private SET_ADDRESS(address: string){
    this.address = address
  }

  @Mutation
  private SET_PHONE(phone: string){
    this.phone = phone
  }

  @Mutation
  private SET_ZIP(zip: string){
    this.zip = zip
  }
}
export const CompanyModule = getModule(Company)