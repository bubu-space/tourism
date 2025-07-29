<template>
	<xn-form-container
		:title="formData.id ? '编辑动态' : '增加动态'"
		:width="500"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" layout="vertical">
      <a-form-item label="标题" name="title">
        <a-input style="width: 100%" v-model:value="formData.title"/>
      </a-form-item>
      <a-form-item label="图片" name="images">
        <xn-upload-mutiple v-model="formData.images" uploadMode="image" :uploadNumber="5" />
      </a-form-item>
      <a-form-item label="景区：" name="spotId">
        <a-select v-model:value="formData.spotId" placeholder="选择景区" style="width: 100%" :options="spotList" />
      </a-form-item>
      <a-form-item label="追溯时间点" name="tracingTime" format="YYYY-MM-DD">
        <a-date-picker v-model:value="formData.tracingTime" style="width: 100%;" />
      </a-form-item>
      <a-form-item label="描述" name="description">
        <a-textarea style="width: 100%" v-model:value="formData.description" />
      </a-form-item>
		</a-form>
		<template #footer>
			<a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
			<a-button type="primary" :loading="submitLoading" @click="onSubmit">保存</a-button>
		</template>
	</xn-form-container>
</template>

<script setup name="orgForm">
	import historyApi from '@/api/biz/historyApi'
	import spotApi from '@/api/biz/spotApi';
  import dayjs from 'dayjs';
  import weekday from 'dayjs/plugin/weekday'
	import localeData from 'dayjs/plugin/localeData'
	dayjs.extend(weekday)
	dayjs.extend(localeData)

	const emit = defineEmits({ successful: null })
	const visible = ref(false)
	const formRef = ref()
	const formData = ref({
    tracingTime: dayjs()
  })
	const submitLoading = ref(false)
  const spotList = ref([])

	// 打开抽屉
	const onOpen = (id) => {
    formData.value = {}
		visible.value = true
		if (id) {
			formData.value.id = id
      historyApi.detail({id}).then((data) => {
				formData.value = Object.assign({}, data)
        formData.value.tracingTime = dayjs(formData.value.tracingTime, "YYYY-MM-DD")
			})
		}
    spotApi.options().then((data) => {
      spotList.value = data.map((item) => ({label: item.spotName, value: item.id}))
    })
	}

	// 关闭抽屉
	const onClose = () => {
		visible.value = false
	}


  // 提交表单
	const onSubmit = () => {
		formRef.value.validate().then(() => {
			submitLoading.value = true
			historyApi
				.submitForm(formData.value, formData.value.id)
				.then(() => {
					visible.value = false
					emit('successful')
				})
				.finally(() => {
					submitLoading.value = false
				})
		})
	}



	defineExpose({
		onOpen
	})
</script>
