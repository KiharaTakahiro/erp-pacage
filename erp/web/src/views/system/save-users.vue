<template>
  <div class="app-container">
    <div>{{ $t("route.createUsre") }}</div>
    <br>
    <br>
    <el-form
      ref="user"
      :model="user"
      autocomplete="on"
      label-position="left">
      <user-id
        :userId.sync="user.userId"/>
      <first-name
        :firstName.sync="user.firstName"/>
      <last-name
        :lastName.sync="user.lastName"/>
      <email
        :email.sync="user.email"/>
      <password
      :password.sync="user.password"
      :password2.sync="user.password2"/>
      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent="createUser">
            {{ $t('client.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import { UserModule } from '@/store/modules/user'
import '@/assets/custom-theme/index.css'
import UserId  from "@/views/components/user-id.vue"
import FirstName from "@/views/components/first-name.vue"
import LastName from "@/views/components/last-name.vue"
import Email from "@/views/components/email.vue"
import Password from "@/views/components/password.vue"

@Component({
  name: 'user-save',
  components :{
    UserId,
    FirstName,
    LastName,
    Email,
    Password
  }
})
export default class extends Vue {

  private user = {
    userId: UserModule.userId,
    name: UserModule.name,
    firstName: UserModule.firstName,
    lastName: UserModule.lastName,
    email: UserModule.email,
    password: UserModule.password,
    password2: UserModule.password2
  }

  private createUser(){
    (this.$refs.user as ElForm).validate(async(valid: boolean) => {
      if(valid){
        await UserModule.CreateUser(this.user)
        this.$router.push({
          path: 'users'
        }).catch(err => {
          console.warn(err)
        })
      this.$message({
      message: this.$t('components.createClients').toString(),
      type: 'success'
    })
      }else {
        this.$message({
        message: this.$t('components.validation').toString(),
        type: 'error'
        })
        return false
      }
    })
  }
  
}
</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.app-container{
  width: 50%;
}

.box-card {
  width: 400px;
  max-width: 100%;
  margin: 20px auto;
}

.block {
  padding: 30px 24px;
}

.tag-item {
  margin-right: 15px;
}

.complete-btn{
  float: right;
}

</style>