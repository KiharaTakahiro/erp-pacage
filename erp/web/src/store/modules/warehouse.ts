import {
  VuexModule,
  Module,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'
import {
  createWarehouse,
  getWarehouse,
  updateWarehouse,
  infoWarehouse
} from '@/api/warehouse'
import store from '@/store'


export interface IWarehouseState {
  id: string
  warehouseName: string
  list: JSON[]
  totalItem: number
}

@Module({ dynamic: true, store, name: 'warehouse' })
class Warehouse extends VuexModule implements IWarehouseState {
  public id = ''
  public warehouseName = ''
  public list: JSON[] = []
  public totalItem = 0

  @Mutation
  private SET_ID(id: string) {
    this.id = id
  }

  @Mutation
  private SET_NAME(warehouseName: string) {
    this.warehouseName = warehouseName
  }

  
  @Mutation
  private SET_LIST(list: JSON[]) {
    this.list = list
  }

  @Mutation
  private SET_TOTAL_ITEM(totalItem: number) {
    this.totalItem = totalItem
  }

  @Action
  public async CreateWarehouse(warehouseInfo: {
    warehouseName: string
  }) {
    let { warehouseName } = warehouseInfo
    warehouseName = warehouseName.trim()
    await createWarehouse({
      warehouseName: warehouseName,
    })
  }

  @Action
  public setWarehouseName(warehouseName: string) {
    this.SET_NAME(warehouseName)
  }

  @Action({ rawError: true })
  public async EditWarehouse(warehouseInfo: { id: string }) {
    let { id } = warehouseInfo
    const { data } = await getWarehouse({ warehouseSeq: id })
    this.SET_ID(data.warehouseSeq)
    this.SET_NAME(data.warehouseName)
  }

  @Action({ rawError: true })
  public async UpdateWarehouse(warehouseInfo: {
    id: string
    warehouseName: string
  }) {
    let { id, warehouseName } = warehouseInfo
    warehouseName = warehouseName.trim()
    await updateWarehouse({
      warehouseSeq: id,
      warehouseName: warehouseName
    })
  }

  @Action({ rawError: true })
  public async WarehouseList(warehouseInfo: any) {
    const { data } = await infoWarehouse(warehouseInfo)
    console.log(data)
    this.SET_LIST(data.warehouse)
    this.SET_TOTAL_ITEM(data.totalItemsNum)
  }
}
export const WarehouseModule = getModule(Warehouse)