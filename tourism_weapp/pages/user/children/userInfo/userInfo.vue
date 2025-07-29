<template>
	<view>
		<form>
			<view class="cu-form-group margin-top">
				<view class="title">头像</view>
				<button class="avatar" style="margin: 0;" open-type="chooseAvatar" @chooseavatar="onChooseAvatar">
					<image style="width: 100%;height: 100%;" :src="formData.avatar"></image>
				</button> 
			</view>
			<view class="cu-form-group margin-top">
				<view class="title">手机号</view>
				<input placeholder="输入手机号" type="number" v-model:value="formData.phone"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">姓名</view>
				<input placeholder="输入姓名" v-model:value="formData.name"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">性别</view>
				<picker @change="PickerChange" :value="formData.sex" :range="sexPicker">
					<view class="picker">
						{{formData.sex?formData.sex:'选择性别'}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">身份证</view>
				<input placeholder="输入身份证" type="number" v-model:value="formData.idCard"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">密码</view>
				<input placeholder="输入密码" type="password" v-model:value="formData.password"></input>
			</view>
		</form>

		<view class="action">
			<button class="cu-btn lg bg-blue radius" @click="onSubmit">保存修改</button>
		</view>
	</view>
</template>

<script>
	import userApi from "@/api/userApi.js"
	import tool from "@/utils/tool.js"
	import { pathToBase64 } from 'image-tools'
	
	export default {
		data() {
			return {
				sexPicker: ['男', '女'],
				formData: {
					avatar: null,
					sex: null,
					phone: null,
					idCard: null,
					name: null,
					password: null
				}
			}
		},
		onLoad() {
			userApi.getUserInfo().then(res => {
				this.formData = res.data
			})
		},
		methods: {
			onChooseAvatar(e) {
				this.formData.avatar = e.detail.avatarUrl;
				pathToBase64(e.detail.avatarUrl).then((base64) => {
					this.formData.avatar = base64
				})
			},
			PickerChange(e) {
				this.formData.sex = this.sexPicker[e.detail.value]
			},
			onSubmit(){
				userApi.updateUserInfo(this.formData).then(res => {
					uni.showToast({
						icon:'success',
						title:'更新成功'
					})
					tool.data.set("USER_INFO",this.formData)
					uni.navigateBack()
				})
			}
		}
	}
</script>

<style>
	.avatar {
		width: 33px;
		height: 33px;
		border-radius: 4px;
		
		padding: 0 !important;
	}

	.action {
		display: flex;
		flex-direction: column;
		padding: 50px 10px 0 10px;
	}

	.title {
		width: 70px;
		text-align: right;
	}
	
</style>