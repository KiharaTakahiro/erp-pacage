import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators'
import { createRecivedOrder } from '@/api/recived-order'
import store from '@/store'
export interface IRecievedOrderState {
  recievedOrderSeq: string,
  clientsSeq: string,
  companySeq: string,
  departmentSeq: string,
  details: any[],
  quotationSeq: string,
  recievedOrderDate: string,
  tax: number,
  total: number
}

@Module({ dynamic: true, store, name: 'recievedOrder' })
class RecievedOrder extends VuexModule implements IRecievedOrderState {
  public recievedOrderSeq = ''
  public clientsSeq = ''
  public companySeq = ''
  public departmentSeq = ''
  public details:any[] = []
  public quotationSeq = ''
  public recievedOrderDate = ''
  public tax = 0
  public total = 0

  @Mutation
  private SET_RECIEVED_ORDER_ID(id: any){
    this.recievedOrderSeq = id
  }

  @Mutation
  private SET_CLIENT_ID(clientsSeq: any){
    this.clientsSeq = clientsSeq
  }

  @Mutation
  private SET_COMPANY_ID(companySeq: any){
    this.companySeq = companySeq
  }

  @Mutation
  private SET_DEPARTMENT_ID(departmentSeq: any){
    this.departmentSeq = departmentSeq
  }
  @Mutation
  private SET_DETAILS(detail: any){
    this.details.push(detail)
  }
  @Mutation
  private SET_QUOTAION_ID(quotationSeq: any){
    this.quotationSeq = quotationSeq
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
  public async createReciverdOrder(orderInfo:{clientsSeq: string,companySeq: string, departmentSeq: string, details: any[], quotationSeq: string, recievedOrderDate: string, tax: number, total: number}){
    let { clientsSeq,companySeq, departmentSeq, details, quotationSeq, recievedOrderDate, tax, total} = orderInfo
    await createRecivedOrder({clientsSeq: clientsSeq,companySeq: companySeq, departmentSeq: departmentSeq, details: details, quotationSeq: quotationSeq, recievedOrderDate: recievedOrderDate, tax: tax, total: total})
  }

}

export const RecievedOrderModule = getModule(RecievedOrder)