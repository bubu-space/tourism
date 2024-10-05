<template>
	<xn-form-container
		:title="formData.id ? '编辑活动' : '增加活动'"
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
	  	<!-- 10个 -->
        <xn-upload-mutiple v-model="formData.images" uploadMode="image" :uploadNumber="10" />
      </a-form-item>
      <a-form-item label="点赞量" name="likeCount">
        <a-input-number style="width: 100%" v-model:value="formData.likeCount" :min="0" />
      </a-form-item>
      <a-form-item label="浏览量" name="watchCount">
        <a-input-number style="width: 100%" v-model:value="formData.watchCount" :min="0" />
      </a-form-item>
      <a-form-item label="攻略类型" name="category">
        <a-select v-model:value="formData.category" placeholder="选择攻略类型" style="width: 100%" :options="categoryOptions" />
      </a-form-item>
      <a-form-item label="攻略描述" name="describle">
        <a-textarea style="width: 100%" v-model:value="formData.describle" />
      </a-form-item>
		</a-form>
		<template #footer>
			<a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
			<a-button type="primary" :loading="submitLoading" @click="onSubmit">保存</a-button>
		</template>
	</xn-form-container>
</template>

<script setup name="orgForm">
	import strategyApi from '@/api/biz/strategyApi'
	import tool from '@/utils/tool'

  const categoryOptions = tool.dictList("BIZ_STRATEGY")

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
      strategyApi.detail({id}).then((data) => {
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
			strategyApi
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
