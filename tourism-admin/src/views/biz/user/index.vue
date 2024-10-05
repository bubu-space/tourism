<template>
  <a-card :bordered="false" style="margin-bottom: 10px">
    <a-form ref="searchFormRef" layout="inline" :model="searchFormState">
      <a-form-item name="name" label="姓名">
        <a-input v-model:value="searchFormState.name" placeholder="请输入组织名称关键词" style="width: 240px;" />
      </a-form-item>
			<a-form-item name="phone" label="电话">
			  <a-input v-model:value="searchFormState.phone" placeholder="请输入组织名称关键词" style="width: 240px;" />
			</a-form-item>
			<a-form-item name="status" label="状态">
			 <a-radio-group v-model:value="searchFormState.status">
					<a-radio-button value="ENABLE">启用</a-radio-button>
					<a-radio-button value="DISENABLED">禁用</a-radio-button>
				</a-radio-group>
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
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'avatar'">
          <img :src="record.avatar" style="width: 40px;height: 40px;border-radius: 4px;" />
        </template>
				<template v-if="column.dataIndex === 'status'">
					<a-switch :loading="loading" :checked="record.status === 'ENABLE'" @change="editStatus(record)" />
				</template>
        <template v-if="column.dataIndex === 'action'">
          <a-popconfirm title="确定要重置密码吗？" @confirm="restPwd(record)">
            <a-button type="link" danger size="small">重置密码</a-button>
          </a-popconfirm>
        </template>
      </template>
    </s-table>
  </a-card>
		
</template>

<script setup>
	import wxUserApi from '@/api/biz/wxUserApi'

	const columns = [
		{
			title: '姓名',
			dataIndex: 'name'
		},
		{
			title: '手机号',
			dataIndex: 'phone'
		},
		{
			title: '头像',
			dataIndex: 'avatar',
			width: 100
		},
		{
			title: '身份证号',
			dataIndex: 'idCard',
		},
		{
			title: '性别',
			dataIndex: 'sex',
		},
		{
			title: '年龄',
			dataIndex: 'age',
		},
		{
			title: '状态',
			dataIndex: 'status',
		},
		{
			title: '操作',
			dataIndex: 'action',
			align: 'center',
			width: '150px'
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
	// 定义tableDOM
	const loading = ref(false)
	const tableRef = ref(null)
	const formRef = ref()
	const searchFormRef = ref()
	const searchFormState = ref({})


	// 表格查询 返回 Promise 对象
	const loadData = (parameter) => {
		return wxUserApi.page(Object.assign(parameter, searchFormState.value)).then((res) => {
			return res
		})
	}
	// 重置
	const reset = () => {
		searchFormRef.value.resetFields()
		tableRef.value.refresh(true)
	}
	// 修改状态
	const editStatus = (record) => {
		let param = Object.assign({},record)
		param.status = record.status=='ENABLE'?'DISABLED':'ENABLE'
		loading.value = true
		wxUserApi
			.submitForm(param,param.id)
			.then(() => {
				tableRef.value.refresh()
			})
			.finally(() => {
				loading.value = false
			})
	}
	// 重置密码
	const restPwd = (record) => {
		let param = Object.assign({},record)
		param.password = "123456"
		loading.value = true
		wxUserApi
			.submitForm(param,param.id)
			.then(() => {
				tableRef.value.refresh()
			})
			.finally(() => {
				loading.value = false
			})
	}

</script>

<style scoped>
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
