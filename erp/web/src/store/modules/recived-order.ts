import {
  VuexModule,
  Module,
  Mutation,
  Action,
  getModule
} from 'vuex-module-decorators'
import { createRecivedOrder } from '@/api/recived-order'
import store from '@/store'
export interface IRecievedOrderState {
  recivedOrderSeq: string
  clientsSeq: string
  companySeq: string
  departmentSeq: string
  details: RecivedOrderDetail[]
  quotationSeq: string
  recivedOrderDate: string
  tax: number
  total: number
}

interface RecivedOrderDetail {
  productSeq: string
  quantity: string
  discount: number
  deriveryDate: string
  lotSeq: number
  status: string
}
@Module({ dynamic: true, store, name: 'recivedOrder' })
class RecievedOrder extends VuexModule implements IRecievedOrderState {
  public recivedOrderSeq = ''
  public clientsSeq = ''
  public companySeq = ''
  public departmentSeq = ''
  public details: any[] = []
  public quotationSeq = ''
  public recivedOrderDate = ''
  public tax = 0
  public total = 0

  @Mutation
  private SET_RECIEVED_ORDER_ID(id: any) {
    this.recivedOrderSeq = id
  }

  @Mutation
  private SET_CLIENT_ID(clientsSeq: any) {
    this.clientsSeq = clientsSeq
  }

  @Mutation
  private SET_COMPANY_ID(companySeq: any) {
    this.companySeq = companySeq
  }

  @Mutation
  private SET_DEPARTMENT_ID(departmentSeq: any) {
    this.departmentSeq = departmentSeq
  }
  @Mutation
  private SET_DETAILS(detail: RecivedOrderDetail) {
    this.details.push(detail)
  }
  @Mutation
  private SET_QUOTAION_ID(quotationSeq: any) {
    this.quotationSeq = quotationSeq
  }
  @Mutation
  private SET_TAX(tax: number) {
    this.tax = tax
  }
  @Mutation
  private SET_TOTAL(total: number) {
    this.total = total
  }

  @Mutation
  private SET_RECIEVED_ORDER_DATE(date: any) {
    this.recivedOrderDate = date
  }

  @Action
  public setClientsID(id: any) {
    this.SET_CLIENT_ID(id)
  }

  @Action
  public setCompanyId(id: any) {
    this.SET_COMPANY_ID(id)
  }

  @Action
  public setDepartmentId(id: any) {
    this.SET_DEPARTMENT_ID(id)
  }

  @Action
  public pushDetail(detail: RecivedOrderDetail) {
    this.SET_DETAILS(detail)
  }

  @Action
  public setRecievedOrderDate(date: any) {
    this.SET_RECIEVED_ORDER_DATE(date)
  }

  @Action
  public setQuotationId(id: any) {
    this.SET_QUOTAION_ID(id)
  }

  @Action
  public async createReciverdOrder(orderInfo: {
    clientsSeq: string
    companySeq: string
    departmentSeq: string
    details: any[]
    quotationSeq: string
    recivedOrderDate: string
    tax: number
    total: number
  }) {
    let {
      clientsSeq,
      companySeq,
      departmentSeq,
      details,
      quotationSeq,
      recivedOrderDate,
      tax,
      total
    } = orderInfo
    await createRecivedOrder({
      clientsSeq: clientsSeq,
      companySeq: companySeq,
      departmentSeq: departmentSeq,
      details: details,
      quotationSeq: quotationSeq,
      recivedOrderDate: recivedOrderDate,
      tax: tax,
      total: total
    })
  }
}

export const RecievedOrderModule = getModule(RecievedOrder)
