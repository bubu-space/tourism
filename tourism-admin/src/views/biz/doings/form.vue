<template>
	<xn-form-container
		:title="formData.id ? '编辑活动' : '增加活动'"
		width="100%"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="18">
          <editor v-model="formData.content" :file-upload-function="apiFunction.fileUploadApi" :height="900" />
        </a-col>
        <a-col :span="6">
          <a-form-item label="活动封面：" name="image">
            <xn-upload-single v-model="formData.image" uploadMode="image" />
          </a-form-item>
          <a-form-item label="活动标题：" name="title">
            <a-input style="width: 100%" v-model:value="formData.title"/>
          </a-form-item>
          <a-form-item label="景区：" name="spotId">
            <a-select v-model:value="formData.spotId" placeholder="选择景区" style="width: 100%" :options="spotList" />
          </a-form-item>
        </a-col>
      </a-row>
		</a-form>
		<template #footer>
			<a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
			<a-button type="primary" :loading="submitLoading" @click="onSubmit">保存</a-button>
		</template>
	</xn-form-container>
</template>

<script setup name="orgForm">
	import doingsApi from '@/api/biz/doingsApi'
  import spotApi from '@/api/biz/spotApi';
  import fileApi from '@/api/dev/fileApi'


	const emit = defineEmits({ successful: null })
	const visible = ref(false)
	const formRef = ref()
	const formData = ref({})
	const submitLoading = ref(false)
  const spotList = ref([])

	// 打开抽屉
	const onOpen = (id) => {
    formData.value = {}
		visible.value = true
		if (id) {
			formData.value.id = id
      doingsApi.detail({id}).then((data) => {
				formData.value = Object.assign({}, data)
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
			doingsApi
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

  // 传递文件上传需要的API
	const apiFunction = {
		fileUploadApi: (param) => {
			return fileApi.fileUploadDynamicReturnUrl(param).then((data) => {
				return Promise.resolve(data)
			})
		}
	}



	defineExpose({
		onOpen
	})
</script>
