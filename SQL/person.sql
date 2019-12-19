USE [Nutshell]
GO

/****** Object:  Table [dbo].[person]    Script Date: 12/18/2019 13:01:32 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[person]') AND type in (N'U'))
DROP TABLE [dbo].[person]
GO

USE [Nutshell]
GO

/****** Object:  Table [dbo].[person]    Script Date: 12/18/2019 13:01:32 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[person](
	[firstname] [nchar](50) NOT NULL,
	[lastname] [nchar](50) NOT NULL,
 CONSTRAINT [PK_person] PRIMARY KEY CLUSTERED 
(
	[firstname] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


