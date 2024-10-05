<template>
	<a-row :gutter="10">

		<a-col :span="6">
			<a-card :bordered="false" :loading="cardLoading"  title="景区列表">
				<div v-if="spotList.length>0" class="spotList">
          <div v-for="(item,index) in spotList" @click="spotChange(item)" :key="index" class="spotItem">
            <div style="display: flex;align-items: center;gap: 10px;">
              <img :src="item.cover.url" style="width: 60px;height: 60px;border-radius: 4px;" />
              <div style="display: flex;flex-direction: column;gap: 10px; ">
                <div style="display: flex; gap: 20px; color: orange;">
                  <p>评分：{{ item.rate.toFixed(1) }}分</p>
                  <p>星级：{{ item.startLevel }}星</p>
                </div>
                <p style="font-size: 18px;color: #606D80">{{ item.spotName }}</p>
              </div>
            </div>
            
          </div>
        </div>
				<a-empty v-else :image="Empty.PRESENTED_IMAGE_SIMPLE" />
			</a-card>
		</a-col>

		<a-col :span="6">
			<a-card :bordered="false" title="门票列表">
        <div v-if="ticketList.length>0" class="ticketList">
          <div v-for="(item,index) in ticketList" @click="ticketChange(item)" :key="index" class="ticketItem">
            <div style="display: flex; align-items: center; justify-content: space-between;font-size: 18px;">
              <p>{{ item.name }}</p>
              <p style="color: orange">￥{{ item.price.toFixed(2) }}元/人</p>
            </div>
            <p style="color: grey;margin-top: 10px;">说明：{{ item.remark }}</p>
          </div>
        </div>
        <a-empty v-else :image="Empty.PRESENTED_IMAGE_SIMPLE" />
			</a-card>
		</a-col>

    <a-col :span="12">
			<a-card :bordered="false" title="门票信息">
        <a-alert message="购票需谨慎" type="info" show-icon />
        <fieldset>
          <legend>门票信息</legend>
          <h1 style="color: #0680F3;">景区：{{ formData.spotName }}</h1>
          <h1 style="color: #FA532F;">票名：{{ formData.ticketName }}</h1>
          <div style="display: flex; gap: 20px">
            <a-form-item name="ticketCount" label="票数：">
              <a-input-number v-model:value="formData.ticketCount" :min="1" style="width: 200px;" />
            </a-form-item>
            <a-form-item name="ticketCount" label="预定时间：">
              <a-date-picker v-model:value="formData.reservationTime" format="YYYY-MM-DD" style="width: 200px;" />
            </a-form-item>
          </div>
        </fieldset>
        <fieldset style="max-height: 450px;overflow: auto;">
          <legend>用户信息</legend>
          <div v-for="(item,index) in formData.userTickInfos" :key="index" style="margin-bottom: 12px;display: flex;align-items: center;gap: 16px;">
            <a-form-item name="ticketCount" :label="`姓名${index+1}`">
              <a-input v-model:value="item.userName" size="large" style="width: 200px;" />
            </a-form-item>
            <a-form-item name="ticketCount" :label="`手机号${index+1}`">
              <a-input v-model:value="item.userPhone" size="large" style="width: 200px;" />
            </a-form-item>
            <a-form-item name="ticketCount" :label="`身份证号${index+1}`">
              <a-input v-model:value="item.userIdCard" size="large" style="width: 200px;" />
            </a-form-item>
          </div>
        </fieldset>

        <h2 style="color: orange; margin: 50px 0;">总价：￥{{ formData.price.toFixed(2) }}</h2>
        
        <a-space>
          <a-button type="primary" size="large" @click="submit">立即支付</a-button>
          <a-button type="default" size="large">清空</a-button>
        </a-space>
			</a-card>
		</a-col>
	</a-row>

</template>

<script setup name="sysOrg">
	import { Empty } from 'ant-design-vue'
	import spotApi from '@/api/biz/spotApi'
  import ticketApi from '@/api/biz/ticketApi';
  import orderApi from '@/api/biz/orderApi';
  import dayjs from 'dayjs';


  const cardLoading = ref(false)
  const spotList = ref([])
  const ticketList = ref([])  
  const PRICE = ref(0)
  const formData = ref({
    spotId: null,
    spotName: null,
    ticketId: null,
    ticketName: null,
    payMode: "网页",
    ticketCount: null,
    userTickInfos: [],
    reservationTime: dayjs(),
    price: 0
  })

  onMounted(()=>{
    getSpotList()
  })


  watch(()=>formData.value.ticketCount,(newV,oldV) => {
    formData.value.price = newV * PRICE.value
    console.log(newV,oldV);
    if (newV > oldV) {
      formData.value.userTickInfos.push({
        userName: null,
        userPhone: null,
        userIdCard: null
      })  
    }else{
      formData.value.userTickInfos.pop()
    }
  })
  

  const getSpotList = () => {
    spotApi.options().then(data => {
      spotList.value = data
    })
  }

  const spotChange = (spot) => {
    formData.value.spotName = spot.spotName
    formData.value.spotId = spot.id
    ticketApi.page({spotId: spot.id}).then(res => {
      ticketList.value = res.records
    })
  }

  const ticketChange = (item) => {
    formData.value.ticketId = item.id
    formData.value.ticketName = item.name
    PRICE.value = item.price
    formData.value.ticketCount = 1
  }

  const submit = () => {
    let params = Object.assign({},formData.value)
    params.reservationTime = params.reservationTime.format('YYYY-MM-DD')
    orderApi.submitForm(params,null).then(res => {

    })
  }

</script>

<style scoped lang="less">
  p{
    margin: 0;
    padding: 0;
  }

  .spotList, .ticketList{
    height: calc(100vh - 112px - 48px - 50px - 12px - 50px);
    overflow: auto
  }

  .spotItem,.ticketItem{
    padding: 20px 20px;
    border: 1px solid #f2f2f2;
    border-bottom: none;
    cursor: pointer;

    &:last-child{
      border-bottom: 1px solid #f2f2f2;
    }

    &:hover{
      background-color: #F0F2F5;
    }

    &:active{
      background-color: #86c5ff;
      color: #fff;
    }
  }

  fieldset {
    padding: .35em .625em .75em;
    margin: 0 2px;
    border: 1px solid silver;
  }

  legend {
    padding: .5em;
    border: 0;
    width: auto;
    font-size: 18px !important;
  }
</style>
