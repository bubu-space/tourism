<template>
	<xn-form-container
		:title="formData.id ? '编辑门票' : '增加门票'"
		:width="500"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" layout="vertical">
      <a-form-item label="名称" name="name">
        <a-input style="width: 100%" v-model:value="formData.name"/>
      </a-form-item>
      <a-form-item label="价格" name="price">
        <a-input-number style="width: 100%" v-model:value="formData.price" :min="0" />
      </a-form-item>
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label="开始时间" name="startTime">
            <a-time-picker v-model:value="formData.startTime" value-format="HH:mm:ss" style="width: 100%" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="结束时间" name="endTime">
            <a-time-picker v-model:value="formData.endTime" value-format="HH:mm:ss" style="width: 100%" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item label="所属景区：" name="spotId">
        <a-select v-model:value="formData.spotId" placeholder="选择景区" style="width: 100%" :options="spotList" />
      </a-form-item>
      <a-form-item label="最大库存" name="inventoryCount">
        <a-input-number style="width: 100%" v-model:value="formData.maxInventory" :min="0" />
      </a-form-item>
      <a-form-item label="排序" name="sort">
        <a-input-number style="width: 100%" v-model:value="formData.sort" :min="0" />
      </a-form-item>
      <a-form-item label="销售状态" name="status">
        <a-select v-model:value="formData.status" placeholder="选择景区" style="width: 100%" :options="saleOptions" />
      </a-form-item>
      <a-form-item label="门票描述" name="remark">
        <a-textarea style="width: 100%" v-model:value="formData.remark" />
      </a-form-item>
		</a-form>
		<template #footer>
			<a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
			<a-button type="primary" :loading="submitLoading" @click="onSubmit">保存</a-button>
		</template>
	</xn-form-container>
</template>

<script setup name="orgForm">
	import ticketApi from '@/api/biz/ticketApi'
  import spotApi from '@/api/biz/spotApi';
	import tool from '@/utils/tool'

  const saleOptions = tool.dictList("BIZ_TICKET_SALE_STATUS")

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
      ticketApi.detail({id}).then((data) => {
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
			ticketApi
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
