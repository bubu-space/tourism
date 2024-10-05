<template>
  <a-row :gutter="12" style="margin-bottom: 12px;">
    <a-col :span="4">
      <a-card title="当日售票数" size="large">
        <template #extra> <a-tag color="#2db7f5">总计</a-tag></template>
        <div class="statisticalDataItem">
          <p><span class="value" style="color: green;">{{ totalData.dayTitckCount }}</span>张</p>
        </div>
      </a-card>
    </a-col>
    <a-col :span="4">
      <a-card title="当日营业额" size="large">
        <template #extra> <a-tag color="#2db7f5">总计</a-tag></template>
        <div class="statisticalDataItem">
          <p><span class="value" style="color: green;">{{ totalData.dayTotalPrice }}</span>元</p>
        </div>
      </a-card>
    </a-col>
    <a-col :span="4">
      <a-card title="当周售票数" size="large">
        <template #extra> <a-tag color="#2db7f5">总计</a-tag></template>
        <div class="statisticalDataItem">
          <p><span class="value" style="color: blue;">{{ totalData.weekTitckCount }}</span>张</p>
        </div>
      </a-card>
    </a-col>
    <a-col :span="4">
      <a-card title="当周营业额" size="large">
        <template #extra> <a-tag color="#2db7f5">总计</a-tag></template>
        <div class="statisticalDataItem">
          <p><span class="value" style="color: blue;">{{ totalData.weekTotalPrice }}</span>元</p>
        </div>
      </a-card>
    </a-col>
    <a-col :span="4">
      <a-card title="累计售票数" size="large">
        <template #extra> <a-tag color="#2db7f5">总计</a-tag></template>
        <div class="statisticalDataItem">
          <p><span class="value" style="color: orange;">{{ totalData.titckCount }}</span>张</p>
        </div>
      </a-card>
    </a-col>
    <a-col :span="4">
      <a-card title="累计营业额" size="large">
        <template #extra> <a-tag color="#2db7f5">总计</a-tag></template>
        <div class="statisticalDataItem">
          <p><span class="value" style="color: orange;">{{ totalData.totalPrice }}</span>元</p>
        </div>
      </a-card>
    </a-col>
  </a-row>

 <a-row :gutter="12">
     <a-col :span="12">
      <DownloadEchart/>
    </a-col>
    <a-col :span="12">
      <IncreaseEchart/>
    </a-col>
  </a-row> 
</template>




<script setup>
  import IncreaseEchart from "./increaseEchart.vue";
  import DownloadEchart from "./downloadEchart.vue"
  import analysisApi from "@/api/biz/analysisApi.js"
  import { onMounted, ref } from "vue";

  onMounted(()=>init())

  const init = () => {
    analysisApi.getTotalData().then(data=>{
      totalData.value = data
    })
    
  }

  const totalData = ref({})



</script>




<style lang="less" scoped>
  .shortcut :deep(.ant-card-body){
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 16px;
    cursor: pointer;

    p{
      margin-top: 10px;
    }
  }

  .statisticalDataItem{
    display: flex;
    width: fit-content;
    margin: auto;
    
    .value{
      font-size: 3rem;
      font-weight: bold;
      color: #FFBF00;
      font-family: math;
      margin-right: 10px;
    }
  }
</style>
