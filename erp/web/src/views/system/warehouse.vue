// <template>
//   <div class="app-container">
//     <div>{{ $t('route.warehouse') }}</div>
//     <el-card class="box-card">
//       <h5>検索フォーム</h5>
//       <div class="border">
//       <div>
//         <span class="input-label">ID:</span>
//         <el-input
//           placeholder=""
//           v-model="targeWarehouseSeq"
//           style="margin-top:10px; width:5%; margin-right:20px;"
//           clearable
//         >
//         </el-input>
//         <span class="input-label">名前:</span>
//         <el-input
//           placeholder=""
//           prefix-icon="el-icon-search"
//           v-model="searchName"
//           style="margin-top:10px; width:30%;"
//           clearable
//         >
//         </el-input>
//       </div>
//       <div class="right">
//         <el-button
//           size="small"
//           type="info"
//           style="width:45%; margin-top:10px;"
//           @click.native.prevent="resetBtn"
//         >
//           {{ $t('route.reset') }}
//         </el-button>
//         <el-button
//           size="small"
//           type="primary"
//           style="width:45%; margin-top:10px;"
//           @click.native.prevent="searchBtn"
//         >
//           {{ $t('route.search') }}
//         </el-button>
//       </div>
//     </el-card>


