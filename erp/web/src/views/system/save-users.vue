<template>
  <div class="app-container">
    <div>{{ $t("route.createUsre") }}</div>
    <br>
    <br>
    <el-form
      ref="user"
      :model="user"
      autocomplete="on"
      label-position="left"
    >
      <user-id
        :userId="user.userId"
        @userIdSubmit="userIdRecive"
      />
      <first-name
        :firstName="user.firstName"
        @firstNameSubmit="firstNameRecive"
      />
      <last-name
        :lastName="user.lastName"
        @lastaNameSubmit="firstNameRecive"
        />
      <email
        :email="user.email"
        @emailSubmit="emailRecive"
      />

      <password/>
      
      <div class="complete-btn">
        <el-button
            :loading="loading"
            type="primary"
            style="width:100%;"
            @click.native.prevent="createUser"
          >
            {{ $t('client.complete') }}
        </el-button>
      </div>


    </el-form>

    
    
    
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { Dictionary } from 'vue-router/types/router'
import { UserModule } from '@/store/modules/user'
import '@/assets/custom-theme/index.css'
import UserId  from "@/views/components/user-id.vue"
import FirstName from "@/views/components/first-name.vue"
import LastName from "@/views/components/last-name.vue"
import Email from "@/views/components/email.vue"
import Password from "@/views/components/password.vue"
import { component } from 'node_modules/vue/types/umd'



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
    userId: '',
    name: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    password2: ''
  }

  private userIdRecive(userId: any): void {
    this.user.userId = userId
  }

  private firstNameRecive(firstName: any): void {
    this.user.firstName = firstName
  }

  private lastNameRecive(lastName: any): void {
    this.user.lastName = lastName
  }

  private emailRecive(email: any): void {
    this.user.email = email
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