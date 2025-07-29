<template>
	<view>
		
		<!-- draw.io -->
		
		<!-- 轮播图 -->
		<swiper class="screen-swiper" :indicator-dots="true"
			:circular="true" :autoplay="true" interval="3000" duration="500"
			 style="min-height: 260px!important;"
			>
			<swiper-item v-for="(item,index) in swiperList" :key="index">
				<image :src="item.url" v-if="item.type=='image'" style="width: 100%;height: 100%;"></image>
				<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false" objectFit="cover"
					v-if="item.type=='video'"></video>
			</swiper-item>
		</swiper>
		
		<!-- 通知 -->
		<view class="margin-top-sm">
			<uni-notice-bar show-icon scrollable color="#ffaa00" background-color="#fff" :text="notice" />
		</view>
		
		<!-- 导航 -->
		<view class="nav bg-white margin-top-sm padding-sm flex align-center justify-around">
			<view @click="gotoPage('child','/home/children/news/news')" class="nav_item flex flex-direction align-center">
				<view class="icon">	<text class="cuIcon-hotfill"></text>	</view>
				<text class="label">景区动态</text>
			</view>
			<view @click="gotoPage('tabbar','/spot/spot')"  class="nav_item flex flex-direction align-center">
				<view class="icon">	<text class="cuIcon-countdownfill"></text>	</view>
				<text class="label">景点预约</text>
			</view>
			<view @click="gotoPage('child','/home/children/recommend/recommend')"  class="nav_item flex flex-direction align-center">
				<view class="icon">	<text class="cuIcon-favorfill"></text>	</view>
				<text class="label">推荐</text>
			</view>
			<view @click="gotoPage('child','/home/children/heritage/heritage')"  class="nav_item flex flex-direction align-center">
				<view class="icon">	<text class="cuIcon-activity"></text>	</view>
				<text class="label">文化遗产</text>
			</view>
		</view>
	
		
		<view class="card" v-if="userPlain && userPlain.status!=='已完成'">
			<view class="flex align-center justify-between">
				<text class="h3">{{userPlain.title}}</text>
				<text class="text-grey" @click="toPlainDetail(userPlain.id)">详情 <text class="cuIcon-right"></text></text>
			</view>
			<view class="step">
				<uni-steps direction="row" :active="-1" :options="userPlain.content"></uni-steps>
			</view>
		</view>

		
		<!-- 游玩指南 -->
		<view class="guide_container">
			<view @click="gotoPage('tabbar','/strategy/strategy')">
				<text class="h3">官方攻略</text>
				<text>最具代表性的玩法</text>
			</view>
			<view @click="gotoPage('tabbar','/service/service')">
				<text class="h3">景区服务</text>
				<text>游玩保障到位</text>
			</view>
		</view>
		
		<!-- 热门景区 -->
		<view class="padding-sm">
			<view class="spot-list">
				<view class="spot-item" v-for="(item,index) in getredspot" :key="index"  @click="toDetail(item.id)"> 
					<image :src="item.cover.url" style="width: 100%;height: 130px;" />
					<view class="bg-orange cu-tag sm" style="position: absolute;right: 0;top: 0;">{{item.rate}}分</view>
					
					<view class="flex-sub flex flex-direction justify-between padding-sm">
						<view class="h3 title">
							<view class="cu-tag sm bg-red margin-right-sm">{{item.startLevel}}A</view>		
							<text>{{ item.spotName }}</text>
						</view>
						<view class="margin-top-xs">
							<text v-for="a in item.spotTags" class="cu-tag sm bg-blue light">{{a}}</text>
						</view>
						<view class="address weaktext margin-top-xs">{{item.address}}</view>
					</view>
				</view>
			</view>
		</view>
		
		
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"
	
	let swiperList =  [
		{
			id: 0,
			type: 'image',
			url: 'https://whlyj.taian.gov.cn/picture/-1/1902251556057212463.jpg'
		}, 
		{
			id: 1,
			type: 'image',
			url: 'https://whlyj.taian.gov.cn/picture/-1/1902251556069726475.jpg',
		}, 
		{
			id: 2,
			type: 'image',
			url: 'https://whlyj.taian.gov.cn/picture/-1/1902251556008257644.jpg'
		}, 
		{
			id: 3,
			type: 'image',
			url: 'https://whlyj.taian.gov.cn/picture/0/7339006949034b1faf38800a97aa0aa9.jpg'
		},
	]
	
	export default {
		data() {
			return {
				swiperList: swiperList,
				getredspot: [],
				userPlain: {},
				notice: null
			}
		},
		onShow() {
			indexApi.getredspot().then(res => {
				this.getredspot = res.data
			})
			indexApi.getnewplan().then(res => {
				this.userPlain = res.data
				this.userPlain.content = res.data.content.map(item=>({
					title: item.describle,
					desc: item.dateTime
				}))
			})
			indexApi.getconfig({key: 'SNOWY_BIZ_NOTICE'}).then(res => {
				this.notice = res.data
			})
		},
		methods: {
			gotoPage(type,path){
				if (type==='tabbar') {
					uni.switchTab({
						url:'/pages' + path
					})
				} else{
					uni.navigateTo({
						url:'/pages' + path
					})
				}
			},
			toDetail(id){
				uni.navigateTo({
					url:'/pages/spot/detail?id='+id
				})
			},
			toPlainDetail(id){
				uni.navigateTo({
					url:'/pages/user/children/plain/detail?id='+id
				})
			},
			toSpotDetail(id){
				uni.navigateTo({
					url:'/pages/spot/detail?id='+id
				})
			},
			toStragegyDetail(id){
				uni.navigateTo({
					url:'/pages/strategy/detail?id='+id
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	
	.spot-list{
		
		column-count: 2; 
		column-gap: 10px;
		
		.spot-item{
			position: relative;
			break-inside: avoid;
			width: 100%;
			background-color: #fff;
			margin-bottom: 10px;
		}
	}

	.nav{

		.nav_item{
			
			.icon{
				font-size: 28px;
				margin-bottom: 10px;
				width: 50px;
				height: 50px;
				text-align: center;
				line-height: 50px;
				border-radius: 50%;
				color: #fff;
			}
			
			&:nth-child(1) .icon{
				background: linear-gradient(to right,#38D264,#6CDA66);
			}
			&:nth-child(2) .icon{
				background: linear-gradient(to bottom,#25BFBA,#63E6E0);
			}
			&:nth-child(3) .icon{
				background: linear-gradient(to left,#FD8A30,#F9B222);
			}
			&:nth-child(4) .icon{
				background: linear-gradient(to top,#E4419C,#F5B3D7);
			}
			
		}
	}
	
	.guide_container{
		margin-top: 10px;
		display: flex;
		align-items: center;
		gap: 10px;
		padding: 10px;
		background-color: #ffffff; 
		
		&>view {
			color: #fff;
		  background-color: #f0f0f0; /* 网格项背景色，可以根据需要调整 */
			padding: 14px;
			border-radius: 6px;
			flex: 1;
			
			.h3{
				font-size: 17px;
			}
		}
		
		&>view:nth-child(1) {
			background: linear-gradient(to right,#2996FD,#44BAFA);
		}
		
		&>view:nth-child(2) {
			background: linear-gradient(to left,#efbb38,#ffc234);
		}
	}
	
	.step{
		overflow: scroll;
	}
	
	/deep/.uni-steps__row-line-item,
	/deep/.uni-steps__row-text{
		flex: 0!important;
		width: fit-content!important;
		flex-basis: 150px!important;
		flex-shrink: 0!important;
	}
	
	
</style>