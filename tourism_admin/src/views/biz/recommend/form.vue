<template>
	<xn-form-container
		:title="formData.id ? '编辑推荐' : '增加推荐'"
		width="100%"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="18">
          <editor v-model="formData.content" :file-upload-function="apiFunction.fileUploadApi" :height="900"  />
        </a-col>
        <a-col :span="6">
          <a-form-item label="标题" name="title">
            <a-input style="width: 100%" v-model:value="formData.title"/>
          </a-form-item>
          <a-form-item label="图片" name="cover">
            <xn-upload-single v-model="formData.cover" uploadMode="image" />
          </a-form-item>
          <a-form-item label="推荐类型" name="category">
            <a-select v-model:value="formData.category" placeholder="选择推荐类型" style="width: 100%" :options="categoryOptions" />
          </a-form-item>
          <a-form-item label="推荐指数" name="sort">
            <a-input-number style="width: 100%" v-model:value="formData.sort" :min="0" />
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
	import recommendApi from '@/api/biz/recommendApi'
  import fileApi from '@/api/dev/fileApi'
	import tool from '@/utils/tool'

  const categoryOptions = tool.dictList("BIZ_RECOMMEND")

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
      recommendApi.detail({id}).then((data) => {
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
			recommendApi
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
