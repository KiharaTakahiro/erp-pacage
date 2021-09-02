import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createRecivedOrder } from '@/api/recived-order'
import store from '@/store'
export interface IRecievedOrderState {
  recievedOrderId: string,
  clientId: string,
  companyId: string,
  departmentId: string,
  details: any[],
  quotationId: string,
  recievedOrderDate: string,
  tax: number,
  total: number
}

@Module({ dynamic: true, store, name: 'recievedOrder' })
class RecievedOrder extends VuexModule implements IRecievedOrderState {
  public recievedOrderId = ''
  public clientId = ''
  public companyId = ''
  public departmentId = ''
  public details:any[] = []
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
  private SET_DETAILS(detail: any){
    this.details.push(detail)
  }
  @Mutation
  private SET_QUOTAION_ID(quotationId: any){
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

  
  @Mutation
  private SET_RECIEVED_ORDER_DATE(date: any){
    this.recievedOrderDate = date
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

  @Action
  public pushDetail(detail: any){
    this.SET_DETAILS(detail)
  }

  @Action
  public setRecievedOrderDate(date: any){
    this.SET_RECIEVED_ORDER_DATE(date)
  }

  @Action
  public setQuotationId(id: any){
    this.SET_QUOTAION_ID(id)
  }

  @Action
  public async createReciverdOrder(orderInfo:{recievedOrderId: string, clientId: string,companyId: string, departmentId: string, details: any[], quotationId: string, recievedOrderDate: string, tax: number, total: number}){
    let {recievedOrderId, clientId,companyId, departmentId, details, quotationId, recievedOrderDate, tax, total} = orderInfo
    await createRecivedOrder({recievedOrderId: recievedOrderId, clientId: clientId,companyId: companyId, departmentId: departmentId, details: details, quotationId: quotationId, recievedOrderDate: recievedOrderDate, tax: tax, total: total})
  }

}

export const RecievedOrderModule = getModule(RecievedOrder)