<template>
	<a-list item-layout="horizontal" :data-source="data">
		<template #renderItem="{ item }">
			<a-list-item>
				<a-list-item-meta class="list-item-meta">
					<template #title>{{ item.title }}</template>
					<template #description>
						<span class="security-list-description">{{ item.description }}</span>
						<span v-if="item.value"> : </span>
						<span class="security-list-value">{{ item.value }}</span>
					</template>
					<template #avatar>
						<qq-outlined v-if="item.type === 'qq'" class="bind-icon" :style="{ color: '#1890FF' }" />
						<wechat-outlined v-if="item.type === 'weChat'" class="bind-icon" :style="{ color: '#1AAD19' }" />
						<alipay-circle-outlined v-if="item.type === 'AliPay'" class="bind-icon" :style="{ color: '#178bf5' }" />
						<GiteeIcon style="width: 40px" v-if="item.type === 'Gitee'" class="bind-icon" />
					</template>
				</a-list-item-meta>
				<template #actions>
					<a @click="bindCommon(item.type)">{{ item.value ? '修改' : '去绑定' }}</a>
				</template>
			</a-list-item>
		</template>
	</a-list>
	<updatePassword ref="updatePasswordRef" />
</template>

<script setup>
	import { message } from 'ant-design-vue'
	import UpdatePassword from './bindForm/updatePassword.vue'

	const updatePasswordRef = ref()
	// 获取绑定的情况
	const data = [
		{ title: '密码强度', description: '当前密码强度', value: '弱', type: 'password', bindStatus: 0 },
	]
	const bindCommon = (key) => {
		if (key === 'password') {
			updatePasswordRef.value.onOpen()
		} else {
			message.info('开发中')
		}
	}
</script>

<style scoped>
	.list-item-meta {
		align-items: center;
	}
	.bind-icon {
		padding-left: 10px;
		font-size: 30px;
	}
</style>
