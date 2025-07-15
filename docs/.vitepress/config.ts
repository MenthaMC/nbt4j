import { defineConfig } from 'vitepress'

export default defineConfig({
    base: '/easy-nbt',
    titleTemplate: 'EasyNBT | :title',
    themeConfig: {
        siteTitle: 'EasyNBT',

        search: {
            provider: 'local'
        },
        nav: [
            { text: '主页', link: '/' },
            { text: '快速开始', link: '/quick-started' },
            { text: '文档', link: '/docs/write.md' },
        ],
        sidebar: [
            {
                text: "指南",
                collapsed: false,
                items: [{ text: "快速开始", link: "/quick-started" }],
            },
            {
                text: "基础教程",
                collapsed: false,
                items: [
                    { text: "NBT 文件写入", link: "docs/write" },
                    { text: "NBT 文件读取", link: "docs/read" }
                ],
            }
        ],

        socialLinks: [
            { icon: "github", link: "https://github.com/CoderFrish/easy-nbt" },
        ],
    }
})
