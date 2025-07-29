<template>
	<view>
		<form>	
			<view class="cu-form-group margin-top">
				<view class="title">手机号</view>
				<input placeholder="输入手机号" type="number" v-model:value="formData.phone"></input>
			</view>
			<view class="cu-form-group margin-top">
				<view class="title">密码</view>
				<input placeholder="输入密码" type="password" v-model:value="formData.password"></input>
			</view>
		</form>
		<view class="form_item margin-top padding-left-sm">
			<text class="text-blue " @click="toSignup">没有账号？去注册</text>
		</view>
		<view class="action">
			<button @click=onSubmit class="cu-btn lg bg-blue">登录</button>
		</view>
	</view> 
</template>

<script>
	import authApi from "@/api/authApi.js"
	import userApi from "@/api/userApi.js"
	import tool from "@/utils/tool.js"
	
	export default {
		data() {
			return {
				formData: {
					phone: '193232042',
					password: '123456'
				}
			}
		},
		methods: { 
			async onSubmit(){
				const res = await authApi.login(this.formData)
				tool.data.set("TOKEN",res.data)
				
				const res2 = await userApi.getUserInfo()
				tool.data.set("USER_INFO",res2.data)
				
				uni.switchTab({
					url:'/pages/home/home'
				})
			},
			toSignup(){
				uni.navigateTo({
					url:'/pages/user/children/signup/signup'
				})
			}
		}
	}
</script>

<style>
	.avatar{
		width: 33px;
		height: 33px;
		border-radius: 4px;
	}
	
	.action{
		display: flex;
		flex-direction: column;
		padding: 50px 10px 0 10px;
	}
	
	
	.title {
		width: 70px;
		text-align: right;
	}
</style>
