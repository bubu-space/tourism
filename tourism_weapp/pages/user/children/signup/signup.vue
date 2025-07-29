<template>
	<view>
		<form>
			<view class="cu-form-group margin-top">
				<view class="title">头像</view>
				<button v-if="!formData.avatar" class="avatar" style="margin: 0;" open-type="chooseAvatar"
					@chooseavatar="onChooseAvatar">
					<image :src="defaultAvatarUrl"></image>
				</button>
				<view v-else :class="formData.avatar?'':'cu-avatar radius bg-gray'">
					<image class="avatar" :src="formData.avatar" mode=""></image>
				</view>
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
				<view class="title">年龄</view>
				<input placeholder="输入年龄" type="number" v-model:value="formData.age"></input>
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

		<view class="form_item margin-top padding-left-sm">
			<text class="text-blue " @click="toSignin">已有账号？去登录</text>
		</view>
		<view class="action">
			<button @click="onSubmit" class="cu-btn lg bg-blue radius">注册</button>
		</view>
	</view>
</template>

<script>
	import { pathToBase64 } from 'image-tools'
	import authApi from "@/api/authApi.js"
	import tool from "@/utils/tool.js"

	export default {
		data() {
			return {
				sexPicker: ['男', '女'],
				defaultAvatarUrl: 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0',
				formData: {
					avatar: null,
					sex: null,
					age: null,
					phone: null,
					idCard: null,
					name: null,
					password: null
				}
			}
		},
		methods: {
			onSubmit() {
				authApi.register(this.formData).then(res => {
					uni.showToast({
						title: '注册成功',
						icon: 'success',
						success: () => {
							uni.navigateTo({
								url: '/pages/user/children/signin/signin'
							})
						}
					})

				})
			},
			onChooseAvatar(e) {
				this.formData.avatar = e.detail.avatarUrl;
				pathToBase64(e.detail.avatarUrl).then((base64) => {
					this.formData.avatar = base64
				})
			},
			PickerChange(e) {
				this.formData.sex = this.sexPicker[e.detail.value]
			},
			toSignin() {
				uni.navigateTo({
					url: '/pages/user/children/signin/signin'
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