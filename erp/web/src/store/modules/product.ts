import { VuexModule, Module, Action, Mutation, getModule } from 'vuex-module-decorators'
import store from '@/store'

export interface IProductState {
  name: string
}
@Module({ dynamic: true, store, name: 'product' })
class Product extends VuexModule implements IProductState{
  public name = ''
}
export const ProductModule = getModule(Product)