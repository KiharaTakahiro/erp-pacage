import {
  VuexModule,
  Module,
  Mutation,
  Action,
  getModule
} from 'vuex-module-decorators'
import { createRecivedOrder, getRecivedOrder, updateRecivedOrder } from '@/api/recived-order'
import store from '@/store'
export interface IRecivedOrderState {
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

interface RecivedOrderDetailEdit {
  recivedOrderDetailSeq: string
  recivedOrderSeq: string
  productSeq: string
  quantity: string
  discount: number
  deriveryDate: string
  lotSeq: number
  status: string
}
@Module({ dynamic: true, store, name: 'recivedOrder' })
class RecivedOrder extends VuexModule implements IRecivedOrderState {
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
  private SET_DETAILS(detail: RecivedOrderDetail | RecivedOrderDetailEdit) {
    this.details.push(detail)
  }
  @Mutation
  private RESET_DETAILS() {
    this.details = []
  }
  @Mutation
  private REMOVE_DETAILS(index: number){
    this.details.splice(index, 1)
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

  @Mutation
  private SET_EDIT_DRTAILS(details: any){
    this.details = details
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
  public pushDetail(detail: RecivedOrderDetail | RecivedOrderDetailEdit) {
    this.SET_DETAILS(detail)
  }

  @Action
  public setRecivedOrderDate(date: any) {
    this.SET_RECIEVED_ORDER_DATE(date)
  }

  @Action
  public setQuotationId(id: any) {
    this.SET_QUOTAION_ID(id)
  }

  @Action
  public removeDetails(key: number) {
    this.REMOVE_DETAILS(key)
  }

  @Action
  public setEditDetail(details: any) {{
    this.SET_EDIT_DRTAILS(details)
  }}

  @Action
  public reset() {
    this.SET_RECIEVED_ORDER_ID('')
    this.SET_CLIENT_ID('')
    this.SET_COMPANY_ID('')
    this.SET_DEPARTMENT_ID('')
    this.SET_TAX(0)
    this.SET_TOTAL(0)
    this.RESET_DETAILS()
  }

  @Action({ rawError: true })
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

  @Action({ rawError: true})
  public async GetRecivedOrder(orderInfo: { recivedOrderSeq: string }) {
    let { recivedOrderSeq } = orderInfo
    const { data } = await getRecivedOrder({ recivedOrderSeq: recivedOrderSeq })
    const recivedOrder = data

    this.SET_RECIEVED_ORDER_ID(recivedOrder.recivedOrderSeq)
    this.SET_CLIENT_ID(recivedOrder.clientsSeq)
    this.SET_COMPANY_ID(recivedOrder.companySeq)
    this.SET_DEPARTMENT_ID(recivedOrder.departmentSeq)
    this.SET_RECIEVED_ORDER_DATE(recivedOrder.recivedOrderDate)
    this.SET_TAX(recivedOrder.tax)
    this.SET_TOTAL(recivedOrder.total)
    const details = data.details
    this.SET_EDIT_DRTAILS(details)
  }

  @Action
  public async UpdateRecivedOrder(orderInfo: {
    recivedOrderSeq: string
    clientsSeq: string
    companySeq: string
    departmentSeq: string
    details: any[]
    quotationSeq: string
    recivedOrderDate: string
    tax: number
    total: number
  }){
    let {
      recivedOrderSeq,
      clientsSeq,
      companySeq,
      departmentSeq,
      details,
      quotationSeq,
      recivedOrderDate,
      tax,
      total
    } = orderInfo
    await updateRecivedOrder({
      recivedOrderSeq: recivedOrderSeq,
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

export const RecivedOrderModule = getModule(RecivedOrder)