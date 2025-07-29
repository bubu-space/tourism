<template>
  <a-card title="两周内票数销售趋势">
    <template #extra>
      <a-select v-model:value="searchFormState.id" placeholder="选择景区" style="width: 240px" :options="spotList" @change="getData" />
    </template>
    <div ref="container" style="height: 400px;"></div>
  </a-card>
</template>




<script setup>
  import { onMounted, ref } from "vue";
  import analysisApi from "@/api/biz/analysisApi.js"
  import spotApi from '@/api/biz/spotApi'
  import * as echarts from 'echarts';

  onMounted(async()=>{
    const data = await spotApi.options()
    spotList.value = data.map((item) => ({label: item.spotName, value: item.id}))
    searchFormState.value.id = spotList.value[0].value
    getData()
  })

  const container = ref()
  const spotList = ref([])
  const searchFormState = ref({})

  const getData = async(value) => {
    
    if(!value){
      value = searchFormState.value.id
    }
    const res = await analysisApi.getTendData({id:value})

    let myChart = echarts.init(container.value);
    let option = {
      xAxis: {
        type: 'category',
        data: Array.from(res,({date})=>date),
        axisLabel: {
          interval: 0,
          rotate: 40,
          margin: 10
        },
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: Array.from(res,({ticketcount})=>ticketcount),
          type: 'line',
          smooth: true
        }
      ]
    };
    myChart.setOption(option);
  }

  

</script>




<style lang="less" scoped>
  
</style>