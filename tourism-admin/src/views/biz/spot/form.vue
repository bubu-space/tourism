<template>
	<xn-form-container
		:title="formData.id ? '编辑景区' : '增加景区'"
		:width="780"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" layout="vertical">
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="景区名称：" name="spotName">
						<a-input style="width: 100%" v-model:value="formData.spotName"/>
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="景区地址：" name="address">
						<a-input style="width: 100%" v-model:value="formData.address"/>
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="开放时间段：" name="openingTime">
						<a-input style="width: 100%" v-model:value="formData.openingTime"/>
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="是否可预约：" name="isPrebook">
            <a-radio-group v-model:value="formData.isPrebook" name="radioGroup">
              <a-radio value="YES">是</a-radio>
              <a-radio value=" NO">否</a-radio>
            </a-radio-group>
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="经度：" name="longitude">
						<a-input style="width: 100%" v-model:value="formData.longitude"/>
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="纬度：" name="latitude">
						<a-input style="width: 100%" v-model:value="formData.latitude"/>
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="景区标签：" name="spotTags">
						<a-select v-model:value="formData.spotTags" mode="tags" style="width: 100%" />
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="官方网站：" name="websiteUrl">
						<a-input style="width: 100%" v-model:value="formData.websiteUrl"/>
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="联系电话：" name="contactPhone">
						<a-input style="width: 100%" v-model:value="formData.contactPhone"/>
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="景区评分：" name="rate">
						<a-rate v-model:value="formData.rate" />
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="景区星级：" name="startLevel">
						<a-rate v-model:value="formData.startLevel" />
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="景区描述：" name="description">
						<a-textarea style="width: 100%" v-model:value="formData.description" />
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="交通信息：" name="traffice">
						<a-textarea style="width: 100%" v-model:value="formData.traffice"/>
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="景区封面：" name="cover">
						<xn-upload-single v-model="formData.cover" uploadMode="image" />
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="景区地图：" name="plat">
            <xn-upload-single v-model="formData.plat" uploadMode="image" />
					</a-form-item>
				</a-col>
			</a-row>
			<a-row :gutter="20">
				<a-col :span="12">
					<a-form-item label="景区图片：" name="images">
						<xn-upload-mutiple v-model="formData.images" uploadMode="image" :uploadNumber="3" />
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="景区视频：" name="video">
            <xn-upload-mutiple v-model="formData.video" uploadMode="image" :uploadNumber="3" />
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
	import spotApi from '@/api/biz/spotApi'
	import tool from '@/utils/tool'

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
      spotApi.detail({id}).then((data) => {
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
    console.log(formData.value);
		formRef.value.validate().then(() => {
			submitLoading.value = true
			spotApi
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
