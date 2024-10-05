<template>
	<view>
		<!-- 轮播图 -->
		<swiper class="screen-swiper" :indicator-dots="true"
			:circular="true" :autoplay="true" interval="3000" duration="500">
			<swiper-item v-for="(item,index) in swiperList" :key="index">
				<image :src="item.url" mode="aspectFill" v-if="item.type=='image'"></image>
				<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false" objectFit="cover"
					v-if="item.type=='video'"></video>
			</swiper-item>
		</swiper>
		
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
		
		<!-- 通知 -->
		<view class="margin-top-sm">
			<uni-notice-bar show-icon scrollable color="#ffaa00" background-color="#fff" :text="notice" />
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
		<view class="card guide">
			<text class="h2">游玩指南</text>
			<view class="guide_container">
				<view @click="gotoPage('child','/home/children/introduce/introduce')">
					<text class="h2">景区概况</text>
					<text>服务游客 助理旅游</text>
				</view>
				<view @click="gotoPage('tabbar','/strategy/strategy')">
					<text class="h3">官方攻略</text>
					<text>最具代表性的玩法</text>
				</view>
				<view @click="gotoPage('tabbar','/service/service')">
					<text class="h3">景区服务</text>
					<text>游玩保障到位</text>
				</view>
			</view>
		</view>
		
		<!-- 旅游人气榜 -->
		<view class="card rank">
			<text class="h2">旅游人气榜</text>
			<view class="spot">
				<view style="background: linear-gradient(to bottom,#2A83DC,#2A84DA);"><text>景点</text><text>人气榜</text></view>
				<view class="flex flex-direction justify-between" style="height: 74px;">
					<view @click="toSpotDetail(item.id)" v-for="(item,index) in getredspot" :key="item.id">
						<text class="text-bold margin-right-xs">{{index+1}}</text> 
						<text>{{item.spotName}}</text> 
					</view>
				</view>
			</view>
			<view class="strategy margin-top-sm">
				<view style="background: linear-gradient(to top,#F2B7D9,#E977B7);"><text>攻略</text><text>人气榜</text></view>
				<view class="flex flex-direction justify-between" style="height: 74px;">
					<view @click="toStragegyDetail(item.id)" v-for="(item,index) in getredstrategy" :key="item.id">
						<text class="text-bold margin-right-xs">{{index+1}}</text> 
						<text>{{item.title}}</text> 
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
			url: 'https://ct.chuzhou.gov.cn/group4/M00/0D/4C/CpYIZmRu14WAaOW-AAFQsXK-oNA927.png'
		}, 
		{
			id: 1,
			type: 'image',
			url: 'https://ct.chuzhou.gov.cn/group4/M00/0D/4C/CpYIZmRu1zGAEYb-AAfHXN3Y5XA392.png',
		}, 
		{
			id: 2,
			type: 'image',
			url: 'https://ct.chuzhou.gov.cn/group4/M00/0D/4C/CpYIZmRu0waAQX1cAAWSxH0eHF0789.png'
		}, 
		{
			id: 3,
			type: 'image',
			url: 'https://ct.chuzhou.gov.cn/group4/M00/0D/4C/CpYIZmRu1tyAH1MOAAQ1HePKG9w139.png'
		},
	]
	
	export default {
		data() {
			return {
				swiperList: swiperList,
				getredspot: [],
				getredstrategy: [],
				userPlain: {},
				notice: null
			}
		},
		onLoad() {
			indexApi.getredspot().then(res => {
				this.getredspot = res.data
			})
			indexApi.getredstrategy().then(res => {
				this.getredstrategy = res.data
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

	.nav{

		.nav_item{
			
			.icon{
				font-size: 28px;
				margin-bottom: 10px;
				width: 46px;
				height: 46px;
				text-align: center;
				line-height: 46px;
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
		display: grid;
		grid-template-rows: 1fr 1fr; /* 定义两行 */
		grid-template-columns: 1fr 1fr; /* 定义两列 */
	  grid-template-areas:
												"item1 item2"
												"item1 item3"; /* 定义每个网格项的位置 */
		grid-gap: 10px;
		color: #fff;
		
		&>view {
		  background-color: #f0f0f0; /* 网格项背景色，可以根据需要调整 */
			padding: 10px;
			border-radius: 6px;
		}
		
		&>view:nth-child(1) {
		  grid-area: item1; /* 第一列元素占据两行 */
			background: linear-gradient(to top,#70B13B,#84CA45);
		}
		
		&>view:nth-child(2) {
		  grid-area: item2;
			background: linear-gradient(to right,#2996FD,#44BAFA);
		}
		
		&>view:nth-child(3) {
		  grid-area: item3;
			background: linear-gradient(to left,#EC8C1B,#D17D1D);
		}
	}

	.rank{
		display: flex;
		flex-direction: column;
		gap: 10px;
		
		.spot,.strategy{
			display: grid;
			grid-template-columns: 100px 1fr;
			align-items: center;
			
			&>view:nth-child(1){
				width: 80px;
				height: 80px;
				border-radius: 10px;
				background-color: aquamarine;
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				font-size: 14px;
				gap: 4px;
				color: #fff;
			}
			
			&>view:nth-child(2){
				font-size: 13px;
				
				&>view:nth-child(1)>text:nth-child(1){
					color: red;
				}
				&>view:nth-child(2)>text:nth-child(1){
					color: greenyellow;
				}
				&>view:nth-child(3)>text:nth-child(1){
					color: skyblue;
				}
			}
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