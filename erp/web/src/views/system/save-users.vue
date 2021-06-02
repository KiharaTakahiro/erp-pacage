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
      <el-form-item prop="userId">
        <el-input
          ref="user"
          v-model="user.userId"
          :placeholder="$t('user.id')"
          name="userId"
          type="text"
          tabindex="1"
          autocomplete="on"
          maxlength="50"
          style="width:100%; margin-bottom:30px;"
        />
      </el-form-item>
      <el-form-item prop="firstName">
        <el-input
          ref="user"
          v-model="user.firstName"
          :placeholder="$t('user.firstName')"
          name="firstName"
          type="text"
          tabindex="1"
          autocomplete="on"
          max="50"
          style="width:100%; margin-bottom:30px;"
        />
      </el-form-item>
      <el-form-item prop="lastName">
        <el-input
          ref="user"
          v-model="user.lastName"
          :placeholder="$t('user.lastName')"
          name="lastName"
          type="text"
          tabindex="1"
          autocomplete="on"
          max="50"
          style="width:100%; margin-bottom:30px;"
        />
      </el-form-item>
      <el-form-item prop="email">
        <el-input
          ref="user"
          v-model="user.email"
          :placeholder="$t('user.email')"
          name="email"
          type="text"
          tabindex="1"
          autocomplete="on"
          max="50"
          style="width:100%; margin-bottom:30px;"
        />
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          ref="user"
          v-model="user.password"
          :placeholder="$t('user.password')"
          name="password"
          type="text"
          tabindex="1"
          autocomplete="of"
          max="50"
          style="width:100%; margin-bottom:30px;"
        />
      </el-form-item>
      <el-form-item prop="password2">
        <el-input
          ref="user"
          v-model="user.password2"
          :placeholder="$t('user.password2')"
          name="password2"
          type="text"
          tabindex="1"
          autocomplete="of"
          max="50"
          style="width:100%; margin-bottom:30px;"
        />
      </el-form-item>
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


@Component({
  name: 'user-save'
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


  private createUser(){
    (this.$refs.user as ElForm).validate(async(valid: boolean) => {
      if(valid){
        await UserModule.Create(this.user)
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