import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createRecivedOrder } from '@/api/recived-order'
import store from '@/store'
export interface IRecievedOrderState {
  recievedOrderId: string,
  clientId: string,
  companyId: string,
  departmentId: string,
  details: JSON[],
  quotationId: string,
  recievedOrderDate: string,
  tax: number
  total: number
}

@Module({ dynamic: true, store, name: 'recievedOrder' })
class RecievedOrder extends VuexModule implements IRecievedOrderState {
  public recievedOrderId = ''
  public clientId = ''
  public companyId = ''
  public departmentId = ''
  public details:JSON[] = []
  public quotationId = ''
  public recievedOrderDate = ''
  public tax = 0
  public total = 0

  @Mutation
  private SET_RECIEVED_ORDER_ID(id: any){
    this.recievedOrderId = id
  }

  @Mutation
  private SET_CLIENT_ID(clientId: any){
    this.clientId = clientId
  }

  @Mutation
  private SET_COMPANY_ID(companyId: any){
    this.companyId = companyId
  }

  @Mutation
  private SET_DEPARTMENT_ID(departmentId: any){
    this.departmentId = departmentId
  }
  @Mutation
  private SET_DETAILS(detail: JSON){
    this.details.push(detail)
  }
  @Mutation
  private SET_QUOTAION_IS(quotationId: any){
    this.quotationId = quotationId
  }
  @Mutation
  private SET_TAX(tax: number){
    this.tax = tax
  }
  @Mutation
  private SET_TOTAL(total: number){
    this.total = total
  }

  @Action
  public setClientsID(id: any){
    this.SET_CLIENT_ID(id)
  }

  @Action
  public setCompanyId(id: any){
    this.SET_COMPANY_ID(id)
  }

  @Action
  public setDepartmentId(id: any){
    this.SET_DEPARTMENT_ID(id)
  }
}

export const RecievedOrderModule = getModule(RecievedOrder)