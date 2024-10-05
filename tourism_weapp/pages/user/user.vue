<template>
	<view>
		<view class="card-user">
			<view class="pageTitle">用户中心</view>
				
			<view class="user_info" @click="toUserDetail">			
				<view>
					<view class="nickname">{{userInfo.name}}<text class="cuIcon-right"></text></view>
					<view class="phone">No.{{userInfo.phone}}</view>
				</view>
				<view class="avatar">
					<image v-if="userInfo.avatar" :src="userInfo.avatar" style="width: 60px;height: 60px;border-radius: 60%;"></image>
					<text v-else class="cuIcon-emoji"></text>
				</view>
			</view>
		</view>
		
		<view class="cu-list menu sm-border card-menu">
			<view class="cu-item arrow" @click="gotoPage('/user/children/order/order')">
				<view class="content">
					<text class="cuIcon-cartfill text-red"></text>
					<text class="text-grey">我的订单</text>
				</view>
			</view>
			<view class="cu-item arrow" @click="gotoPage('/user/children/plain/plain')">
				<view class="content">
					<text class="cuIcon-creativefill text-blue"></text>
					<text class="text-grey">我的行程规划</text>
				</view>
			</view>
			<view class="cu-item arrow" @click="gotoPage('/user/children/about/about')">
				<view class="content">
					<text class="cuIcon-friendaddfill text-grey"></text>
					<text class="text-grey">关于我们</text>
				</view>
			</view>
		</view>
		
		<button @click="logout" style="display: block;height: 40px;line-height: 40px;" class="cu-btn lg margin-top bg-white">退出登录</button>
		
		<view class="text-center text-gray text-sm margin-top-xl">
			<view>滁州市旅游服务中心</view>
			<view class="margin-top-sm">Copyright@2024,All Rights Reserved</view>
			<view class="margin-top-sm">客服电话：0551-62661646</view>
		</view>
	</view>
</template>

<script>
	import tool from "@/utils/tool.js"
	import authApi from "@/api/authApi.js"
	
	export default {
		data() {
			return {
				userInfo: {}
			}
		},
		onShow() {
			this.userInfo = tool.data.get("USER_INFO") || {}
		},
		methods: {
			gotoPage(path){
				uni.navigateTo({
					url:'/pages' + path
				})
			},
			logout(){
				uni.showModal({
					title: '提示',
					content: '退出后将清除所有用户缓存数据',
					success: () => {
						authApi.logout().then(res => {
							tool.data.clear()
							this.gotoPage('/pages/user/children/signin/signin')
						})
						uni.navigateTo({
							url: '/pages/user/children/signin/signin'
						})
					}
				})
			},
			toUserDetail(){
				uni.navigateTo({
					url:'/pages/user/children/userInfo/userInfo'
				})
			}
		}
	}
</script>

<style lang="scss">
	.card-user{
		height: 180px;
		background: linear-gradient(to bottom,#01C176,#06D964);
		border-radius: 0 0 0 40px;
		color: #FFF;
		padding: 30px 16px 16px 16px;
		
		.pageTitle{
			font-size: 16px;
			text-align: center;
		}
		
		.user_info{
			margin-top: 40px;
			display: grid;
			grid-template-columns: 1fr 60px;
			
			.nickname{
				font-size: 18px;
				
				.cuIcon-right{
					font-size: 14px;
					margin-left: 10px;
				}
			}
			
			.phone{
				font-size: 14px;margin-top: 10px;
			}
			
			
			.avatar{
				height: 60px;
				width: 60px;
				border-radius: 50%;
				font-size: 40px;
				color: #01C176;
				text-align: center;
				line-height: 60px;
				background-color: #fff;
			}
		}
	}
	
	.cu-list{
		margin: 0!important;
		margin-top: 10px!important;
	}
	
	.card-menu{
		border-radius: 0!important;
	}

</style>
