import {
  VuexModule,
  Module,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'
import { login, logout, register } from '@/api/users'
import { getToken, setToken, removeToken } from '@/utils/cookies'
import router, { resetRouter } from '@/router'
import { PermissionModule } from './permission'
import { TagsViewModule } from './tags-view'
import store from '@/store'
import { createSecureServer } from 'node:http2'
export interface IUserState {
  token: string
  userId: string
  name: string
  firstName: string
  lastName: string
  avatar: string
  introduction: string
  roles: string[]
  email: string
  password: string
  password2: string
}

@Module({ dynamic: true, store, name: 'user' })
class User extends VuexModule implements IUserState {
  public token = getToken() || ''
  public userId = ''
  public name = ''
  public firstName = ''
  public lastName = ''
  public avatar = ''
  public introduction = ''
  public roles: string[] = []
  public email = ''
  public password = ''
  public password2 = ''

  @Mutation
  private SET_TOKEN(token: string) {
    this.token = token
  }

  @Mutation
  private SET_NAME(name: string) {
    this.name = name
  }

  @Mutation
  private SET_AVATAR(avatar: string) {
    this.avatar = avatar
  }

  @Mutation
  private SET_INTRODUCTION(introduction: string) {
    this.introduction = introduction
  }

  @Mutation
  private SET_ROLES(roles: string[]) {
    this.roles = roles
  }

  @Mutation
  private SET_USER_ID(userId: string) {
    this.userId = userId
  }

  @Mutation
  private SET_FIRSTNAME(firstName: string) {
    this.firstName = firstName
  }

  @Mutation
  private SET_LASTNAME(lastName: string) {
    this.lastName = lastName
  }

  @Mutation
  private SET_EMAIL(email: string) {
    this.email = email
  }

  @Mutation
  private SET_PASSWORD(password: string) {
    this.password = password
  }

  @Mutation
  private SET_PASSWORD2(password2: string) {
    this.password2 = password2
  }

  @Action
  public async Login(userInfo: { username: string, password: string }) {
    let { username, password } = userInfo
    username = username.trim()
    const { data } = await login({ userId: username, password: password })
    setToken(data.token)
    this.SET_TOKEN(data.token)
  }

  @Action
  public ResetToken() {
    removeToken()
    this.SET_TOKEN('')
    this.SET_ROLES([])
  }

  @Action
  public async GetUserInfo() {
    if (this.token === '') {
      throw Error('GetUserInfo: token is undefined!')
    }
    // TODO: ????????????????????????????????????
    // const { data } = await getUserInfo({ /* Your params here */ })
    // if (!data) {
    //   throw Error('Verification failed, please Login again.')
    // }
    // const { roles, name, avatar, introduction, email } = data.user
    // // roles must be a non-empty array
    // if (!roles || roles.length <= 0) {
    //   throw Error('GetUserInfo: roles must be a non-null array!')
    // }
    this.SET_ROLES(['admin'])
    this.SET_NAME('')
    this.SET_AVATAR('')
    this.SET_INTRODUCTION('')
    this.SET_EMAIL('')
  }

  @Action
  public async ChangeRoles(role: string) {
    // Dynamically modify permissions
    const token = role + '-token'
    this.SET_TOKEN(token)
    setToken(token)
    await this.GetUserInfo()
    resetRouter()
    // Generate dynamic accessible routes based on roles
    PermissionModule.GenerateRoutes(this.roles)
    // Add generated routes
    router.addRoutes(PermissionModule.dynamicRoutes)
    // Reset visited views and cached views
    TagsViewModule.delAllViews()
  }

  @Action
  public async LogOut() {
    if (this.token === '') {
      throw Error('LogOut: token is undefined!')
    }
    removeToken()
    resetRouter()

    // Reset visited views and cached views
    TagsViewModule.delAllViews()
    this.SET_TOKEN('')
    this.SET_ROLES([])
  }

  @Action
  public async CreateUser(userInfo: {
    userId: string
    firstName: string
    lastName: string
    email: string
    password: string
  }) {
    let { userId, firstName, lastName, email, password } = userInfo
    userId = userId.trim()
    firstName = firstName.trim()
    lastName = lastName.trim()
    email = email.trim()
    password = password.trim()
    await register({
      email: email,
      firstName: firstName,
      password: password,
      userId: userId
    })
  }
}

export const UserModule = getModule(User)
