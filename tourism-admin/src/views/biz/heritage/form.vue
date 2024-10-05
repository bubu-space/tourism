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
          <editor v-model="formData.description" :file-upload-function="apiFunction.fileUploadApi" :height="900" />
        </a-col>
        <a-col :span="6">
          <a-form-item label="遗产名称" name="title">
            <a-input style="width: 100%" v-model:value="formData.title"/>
          </a-form-item>
          <a-form-item label="图片" name="image">
            <xn-upload-single v-model="formData.image" uploadMode="image" />
          </a-form-item>
          <a-form-item label="类型" name="category">
            <a-select v-model:value="formData.category" placeholder="选择遗产类型" style="width: 100%" :options="heritageTypeOptions" />
          </a-form-item>
          <a-form-item label="遗产地点" name="address">
            <a-textarea v-model:value="formData.address" placeholder="请输入遗产地点" />
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
	import heritageApi from '@/api/biz/heritageApi'
  import fileApi from '@/api/dev/fileApi'
  import tool from "@/utils/tool"

  
  const heritageTypeOptions = tool.dictList('BIZ_HERITAGE_TYPE')
	const emit = defineEmits({ successful: null })
	const visible = ref(false)
	const formRef = ref()
	const formData = ref({})
	const submitLoading = ref(false)

	// 打开抽屉
	const onOpen = (id) => {
    formData.value = {}
		visible.value = true
		if (id) {
			formData.value.id = id
      heritageApi.detail({id}).then((data) => {
				formData.value = Object.assign({}, data)
			})
		}
	}

	// 关闭抽屉
	const onClose = () => {
		visible.value = false
	}


  // 提交表单
	const onSubmit = () => {
		formRef.value.validate().then(() => {
			submitLoading.value = true
			heritageApi
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
