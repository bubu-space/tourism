<template>
	
  <a-card :bordered="false" style="margin-bottom: 10px">
    <a-form ref="searchFormRef" layout="inline" :model="searchFormState">
      <a-form-item name="id" label="订单ID">
        <a-input v-model:value="searchFormState.id" placeholder="请输入订单ID" style="width: 240px;" />
      </a-form-item>
			<a-form-item>
				<a-space>
					<a-button type="primary" @click="tableRef.refresh(true)">
						<template #icon><SearchOutlined /></template>
						查询
					</a-button>
					<a-button class="snowy-buttom-left" @click="reset">
						<template #icon><redo-outlined /></template>
						重置
					</a-button>
				</a-space>
			</a-form-item>
    </a-form>
  </a-card>
	
  <a-card :bordered="false">
    <s-table
      ref="tableRef"
      :columns="columns"
      :data="loadData"
      :expand-row-by-click="true"
      :alert="options.alert.show"
      bordered
      :row-key="(record) => record.id"
      :row-selection="options.rowSelection"
    >
			<template #operator class="table-operator">
				<a-space>
					<a-button type="primary" @click="formRef.onOpen()">
						<template #icon><plus-outlined /></template>	新增
					</a-button>
				</a-space>
			</template>
    </s-table>
  </a-card>
		
</template>

<script setup>
	import orderApi from '@/api/biz/orderApi'

	// -----------------表格配置------------------------
	const columns = [
    {
			title: '用户ID',
			dataIndex: 'userId',
		},
    {
			title: '预约时间',
			dataIndex: 'reservationTime',
		},
    {
			title: '支付方式',
			dataIndex: 'payMode',
		},
		{
			title: '金额',
			dataIndex: 'price',
		},
    {
			title: '门票id',
			dataIndex: 'ticketId',
		},
    {
			title: '门票数量',
			dataIndex: 'ticketCount',
		},
    {
			title: '订单状态',
			dataIndex: 'status',
		}
	]
	const selectedRowKeys = ref([])
	// 列表选择配置
	const options = {
		alert: {
			show: false,
			clear: () => {
				selectedRowKeys.value = ref([])
			}
		},
		rowSelection: {
			onChange: (selectedRowKey, selectedRows) => {
				selectedRowKeys.value = selectedRowKey
			}
		}
	}

	const tableRef = ref(null)
	const searchFormRef = ref()
	const searchFormState = ref({})

	// 表格查询 返回 Promise 对象
	const loadData = (parameter) => {
    parameter.status = "FINISH"
		return orderApi.page(Object.assign(parameter, searchFormState.value)).then((res) => {
			return res
		})
	}


	// 重置
	const reset = () => {
		searchFormRef.value.resetFields()
		tableRef.value.refresh(true)
	}

</script>



<style scoped lang="less">
	.ant-form-item {
		margin-bottom: 0 !important;
	}
	.primaryAdd {
		margin-right: 10px;
	}
	.snowy-buttom-left {
		margin-left: 8px;
	}
</style>
