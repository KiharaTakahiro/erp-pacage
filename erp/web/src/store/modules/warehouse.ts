import {
  VuexModule,
  Module,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'
import {
  createWarehouse,
} from '@/api/warehouse'
import store from '@/store'


export interface IWarehouseState {
  id: string
  warehouseName: string
}

@Module({ dynamic: true, store, name: 'warehouse' })
class Warehouse extends VuexModule implements IWarehouseState {
  public id = ''
  public warehouseName = ''

  
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
}
export const WarehouseModule = getModule(Warehouse)